package org.mybatis.generator.codegen.mybatis3.service;

import org.mybatis.generator.Config;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
public class ServiceGenerator extends AbstractJavaGenerator {
    public List<CompilationUnit> getCompilationUnits() {
        String basePackage= Config.servicePackage+".impl";

        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(basePackage+"."+table.getDomainObjectName()+"ServiceImpl");

        //Interface interfaze = new Interface(type);
        TopLevelClass topLevelClass=new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        String superService = Config.baseService;
        FullyQualifiedJavaType baseService = new FullyQualifiedJavaType(
                superService);
        FullyQualifiedJavaType entityType= new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        baseService.addTypeArgument(entityType);
        FullyQualifiedJavaType keyType= new FullyQualifiedJavaType("String");
        baseService.addTypeArgument(keyType);
        topLevelClass.setSuperClass(baseService);
        FullyQualifiedJavaType superInface= new FullyQualifiedJavaType(Config.servicePackage+"."+table.getDomainObjectName()+"Service");
        topLevelClass.addSuperInterface(superInface);


        Field field=new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaMapperType()));
        field.setName("mapper");

        field.addAnnotation("@Resource");

        topLevelClass.addField(field);


        Method method=getGetter(field);
        topLevelClass.addMethod(method);

        topLevelClass.addImportedType("javax.annotation.Resource");
        topLevelClass.addImportedType("org.springframework.stereotype.Service");
        topLevelClass.addImportedType(superInface);
        topLevelClass.addImportedType(entityType);
        topLevelClass.addImportedType(this.introspectedTable.getMyBatis3JavaMapperType());
        //topLevelClass.addImportedType(baseService);
        topLevelClass.addAnnotation("@Service");

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(topLevelClass);
        return answer;
    }
}
