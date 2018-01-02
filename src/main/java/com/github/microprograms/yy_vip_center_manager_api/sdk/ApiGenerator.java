package com.github.microprograms.yy_vip_center_manager_api.sdk;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.expr.AssignExpr.Operator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.microprograms.micro_api_sdk.model.ApiDefinition;
import com.github.microprograms.micro_api_sdk.model.EngineDefinition;
import com.github.microprograms.micro_api_sdk.utils.ApiDocumentForShowdocUtils;
import com.github.microprograms.micro_api_sdk.utils.ApiEngineGeneratorUtils;
import com.github.microprograms.micro_api_sdk.utils.api_engine_generator_callback.SmartCallback;
import com.github.microprograms.yy_vip_center_manager_api.utils.Commons;

public class ApiGenerator {
    public static void main(String[] args) throws IOException {
        publicApi();
        publicApiForShowdoc();
    }

    public static void publicApi() throws IOException {
        String srcFolder = "src/main/java";
        ApiEngineGeneratorUtils.setCallback(new MyCallback());
        EngineDefinition engineDefinition = ApiEngineGeneratorUtils.buildEngineDefinition("design/public-api.json");
        ApiEngineGeneratorUtils.deleteModelJavaFiles(srcFolder, engineDefinition);
        ApiEngineGeneratorUtils.createModelJavaFiles(srcFolder, engineDefinition);
        ApiEngineGeneratorUtils.updateApiJavaFiles(srcFolder, engineDefinition);
        ApiEngineGeneratorUtils.deleteUnusedApiJavaFiles(srcFolder, engineDefinition);
        ApiEngineGeneratorUtils.updateErrorCodeJavaFile(srcFolder, engineDefinition);
    }

    public static void publicApiForShowdoc() throws IOException {
        EngineDefinition engineDefinition = ApiEngineGeneratorUtils.buildEngineDefinition("design/public-api.json");
        ApiDocumentForShowdocUtils.update(engineDefinition);
    }

    public static class MyCallback extends SmartCallback {
        @Override
        protected void add(String entityName, ClassOrInterfaceDeclaration apiClassDeclaration, ApiDefinition apiDefinition, CompilationUnit cu) {
            super.add(entityName, apiClassDeclaration, apiDefinition, cu);
            // getOperator
            MethodDeclaration getOperatorMethodDeclaration = getMethod(apiClassDeclaration, "getOperator", getRequestType(apiDefinition));
            BlockStmt getOperatorMethodBody = getOperatorMethodDeclaration.getBody().get();
            Statement getOperatorMethodBodyStatement = getOperatorMethodBody.getStatements().get(0);
            if (getOperatorMethodBodyStatement instanceof ReturnStmt) {
                ReturnStmt returnStmt = (ReturnStmt) getOperatorMethodBodyStatement;
                Expression expression = returnStmt.getExpression().get();
                if (expression instanceof NullLiteralExpr) {
                    cu.addImport(Commons.class);
                    getOperatorMethodBodyStatement.remove();
                    getOperatorMethodBody.addStatement(new ReturnStmt(new MethodCallExpr(new NameExpr(Commons.class.getSimpleName()), new SimpleName("buildOperator"), NodeList.nodeList(new ClassExpr(new ClassOrInterfaceType(apiDefinition.getJavaClassName())), new MethodCallExpr(new NameExpr("req"), "getToken")))));
                    getOperatorMethodDeclaration.addThrownException(Exception.class);
                }
            }
            // buildEntity
            String buildEntityMethodName = String.format("build%s", entityName);
            MethodDeclaration buildEntityMethodDeclaration = getMethod(apiClassDeclaration, buildEntityMethodName, getRequestType(apiDefinition));
            BlockStmt buildEntityMethodBody = buildEntityMethodDeclaration.getBody().get();
            Statement buildEntityMethodBodyStatement = buildEntityMethodBody.getStatements().get(0);
            if (buildEntityMethodBodyStatement instanceof ReturnStmt) {
                ReturnStmt returnStmt = (ReturnStmt) buildEntityMethodBodyStatement;
                Expression expression = returnStmt.getExpression().get();
                if (expression instanceof NullLiteralExpr) {
                    buildEntityMethodBodyStatement.remove();
                    buildEntityMethodBody.addStatement(new AssignExpr(new VariableDeclarationExpr(new ClassOrInterfaceType(entityName), StringUtils.uncapitalize(entityName)), new ObjectCreationExpr(null, new ClassOrInterfaceType(entityName), NodeList.nodeList()), Operator.ASSIGN));
                    buildEntityMethodBody.addStatement(new ReturnStmt(new NameExpr(StringUtils.uncapitalize(entityName))));
                }
            }
        }
    }
}
