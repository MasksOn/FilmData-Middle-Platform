package com.K.government.service;

import com.K.government.bean.SourceTarget;
import com.K.government.sqlparser.test.SQLBaseVisitor;
import com.K.government.sqlparser.test.SQLLexer;
import com.K.government.sqlparser.test.SQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;

public class TableLineage extends SQLBaseVisitor<Object> {
    private final List<String> inputTables = new ArrayList<>();
    private String outputTable;

    @Override
    public Object visitInsertStatement(SQLParser.InsertStatementContext ctx) {
        if (ctx.idPath() != null) {
            outputTable = ctx.idPath().getText();
        }
        return super.visitInsertStatement(ctx);
    }

    @Override
    public Object visitFromClause(SQLParser.FromClauseContext ctx) {
        if (ctx.idPath() != null) {
            inputTables.add(ctx.idPath().getText());
        }
        return super.visitFromClause(ctx);
    }

    @Override
    public Object visitJoinClause(SQLParser.JoinClauseContext ctx) {
        if (ctx.idPath() != null) {
            inputTables.add(ctx.idPath().getText());
        }
        return super.visitJoinClause(ctx);
    }
    public static List<SourceTarget> lineage(String sql) {
        var stream = CharStreams.fromString(sql);
        var lexer = new SQLLexer(stream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new SQLParser(tokens);
        var ast = parser.statement();
        var tableLineage = new TableLineage();
        tableLineage.visit(ast);

        var edges = new ArrayList<SourceTarget>();
        for (var source : tableLineage.inputTables) {
            // 防止解析非 INSERT 语句时 outputTable 为 null
            if (tableLineage.outputTable != null) {
                edges.add(new SourceTarget(source, tableLineage.outputTable));
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        // 你可以尝试放入你那条最复杂的宽表 SQL 测试一下
        String sql = "INSERT INTO dws_baize_dw.dws_movie_info_wide SELECT * FROM dwd_baize_dw.dwd_movie_info JOIN dwd_baize_dw.dwd_link_info ON 1=1";
        
        System.out.println(lineage(sql));
    }
}