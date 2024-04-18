terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }
  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "us-west-2"
  shared_config_files = ["C:/Users/josep/.aws/config"]
  shared_credentials_files = ["C:/Users/josep/.aws/credentials"]
}

data "aws_iam_policy_document" "lambda_policy_doc" {
  statement {
    actions   = ["sts:AssumeRole"]
    effect    = "Allow"
    principals {
      identifiers = ["lambda.amazonaws.com"]
      type        = "Service"
    }
  }
}

resource "aws_iam_role" "lambda_execution_role" {
  name               = "lambda_execution_role"
  assume_role_policy = data.aws_iam_policy_document.lambda_policy_doc.json
}

variable "lambda_functions" {
  type = map(object({
    handler = string
    path    = string
  }))
  default = {
    title = {
      handler = "app.net.lambda.TitleSearchHandler::handleRequest",
      path    = "title"
    },
    author = {
      handler = "app.net.lambda.AuthorSearchHandler::handleRequest",
      path    = "author"
    },
    topic = {
      handler = "app.net.lambda.TopicSearchHandler::handleRequest",
      path    = "topic"
    }
  }
}

resource "aws_lambda_function" "search_lambda" {
  for_each = var.lambda_functions

  function_name = "${each.key}Search"
  filename      = "target/module-14.jar"
  handler       = each.value.handler
  role          = aws_iam_role.lambda_execution_role.arn
  runtime       = "java17"
}

resource "aws_api_gateway_rest_api" "api_gateway" {
  name        = "204-final-gateway"
  description = "Gateway for final project"
}

resource "aws_api_gateway_resource" "api_resource" {
  for_each    = var.lambda_functions
  rest_api_id = aws_api_gateway_rest_api.api_gateway.id
  parent_id   = aws_api_gateway_rest_api.api_gateway.root_resource_id
  path_part   = each.value.path
}

resource "aws_api_gateway_method" "api_method" {
  for_each      = var.lambda_functions
  rest_api_id   = aws_api_gateway_rest_api.api_gateway.id
  resource_id   = aws_api_gateway_resource.api_resource[each.key].id
  http_method   = "GET"
  authorization = "NONE"
}

resource "aws_api_gateway_integration" "api_integration" {
  for_each                = var.lambda_functions
  rest_api_id             = aws_api_gateway_rest_api.api_gateway.id
  resource_id             = aws_api_gateway_resource.api_resource[each.key].id
  http_method             = aws_api_gateway_method.api_method[each.key].http_method
  type                    = "AWS_PROXY"
  integration_http_method = "POST"
  uri                     = aws_lambda_function.search_lambda[each.key].invoke_arn
}

resource "aws_lambda_permission" "api_lambda_permission" {
  for_each = var.lambda_functions

  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.search_lambda[each.key].function_name
  principal     = "apigateway.amazonaws.com"
  source_arn    = "${aws_api_gateway_rest_api.api_gateway.execution_arn}/*/${aws_api_gateway_method.api_method[each.key].http_method}${aws_api_gateway_resource.api_resource[each.key].path}"
}

resource "aws_api_gateway_deployment" "api_deployment" {
  depends_on  = [aws_api_gateway_integration.api_integration]
  rest_api_id = aws_api_gateway_rest_api.api_gateway.id
  stage_name  = "v1"
}