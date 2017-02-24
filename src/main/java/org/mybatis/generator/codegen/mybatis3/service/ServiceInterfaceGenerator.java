package org.mybatis.generator.codegen.mybatis3.service;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
public class ServiceInterfaceGenerator extends AbstractJavaGenerator {

    public List<CompilationUnit> getCompilationUnits() {
        String basePackage="com.ffzx.kart.service";

        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedTable table = this.introspectedTable.getFullyQualifiedTable();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(basePackage+"."+table.getDomainObjectName()+"Service");

        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);
        String rootInterface = "com.ffzx.core.service.BaseService";
        FullyQualifiedJavaType baseService = new FullyQualifiedJavaType(
                rootInterface);
        FullyQualifiedJavaType entityType= new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        baseService.addTypeArgument(entityType);
        FullyQualifiedJavaType keyType= new FullyQualifiedJavaType("String");
        baseService.addTypeArgument(keyType);
        interfaze.addSuperInterface(baseService);
        //interfaze.addImportedType(baseService);

        interfaze.addImportedType(entityType);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(interfaze);
        return answer;
    }
}
