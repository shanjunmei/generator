package org.mybatis.generator.codegen.mybatis3.controller;

import org.mybatis.generator.Config;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
public class ControllerGenerator extends AbstractJavaGenerator {
    public List<CompilationUnit> getCompilationUnits() {
        String basePackage= Config.controllerPackage;

        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(basePackage+"."+table.getDomainObjectName()+"Controller");

        //Interface interfaze = new Interface(type);
        TopLevelClass topLevelClass=new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        String superController = Config.baseController;
        FullyQualifiedJavaType baseController = new FullyQualifiedJavaType(
                superController);
        FullyQualifiedJavaType entityType= new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        baseController.addTypeArgument(entityType);
        FullyQualifiedJavaType keyType= new FullyQualifiedJavaType("String");
        baseController.addTypeArgument(keyType);
        FullyQualifiedJavaType exampleType= new FullyQualifiedJavaType(introspectedTable.getExampleType());
        baseController.addTypeArgument(exampleType);
        topLevelClass.setSuperClass(baseController);


        FullyQualifiedJavaType service= new FullyQualifiedJavaType(Config.servicePackage+"."+table.getDomainObjectName()+"Service");
        Field field=new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(service);
        field.setName("service");

        field.addAnnotation("@Resource");

        topLevelClass.addField(field);


        Method method=getGetter(field);
        topLevelClass.addMethod(method);

        topLevelClass.addImportedType("javax.annotation.Resource");
        topLevelClass.addImportedType("org.springframework.stereotype.Controller");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.RequestMapping");
        topLevelClass.addImportedType(service);
        topLevelClass.addImportedType(entityType);
        topLevelClass.addImportedType(exampleType);
       // topLevelClass.addImportedType(baseController);

        //topLevelClass.addImportedType(baseService);
        topLevelClass.addAnnotation("@Controller");
        topLevelClass.addAnnotation("@RequestMapping(\"/"+table.getDomainObjectName()+"\")");

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(topLevelClass);
        return answer;
    }
}
