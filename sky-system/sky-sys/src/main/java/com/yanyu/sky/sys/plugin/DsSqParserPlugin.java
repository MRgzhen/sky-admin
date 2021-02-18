package com.yanyu.sky.sys.plugin;

import com.github.mrgzhen.core.mybatis.SqlParser;
import com.github.mrgzhen.core.mybatis.SqlParserPlugin;
import com.github.mrgzhen.core.mybatis.SqlParserRule;

/**
 * @author yanyu
 */
public class DsSqParserPlugin implements SqlParserPlugin {

    @Override
    public SqlParser build(SqlParserRule sqlParserRule) {
        SqlParser parser = sqlParserRule.getParser();
        if(parser == null) {
        }
        return parser;
    }

    @Override
    public boolean supports(SqlParserRule delimiter) {
        return true;
    }
}
