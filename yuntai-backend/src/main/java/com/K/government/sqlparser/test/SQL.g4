grammar SQL;

// ==========================================
// Parser Rules (语法规则)
// ==========================================

statement : insertStatement (';')? EOF
          | selectStatement (';')? EOF
          ;

// 把 T_QIDENTIFIER 替换成了 idPath
insertStatement : T_INSERT (T_INTO | T_OVERWRITE) T_TABLE? idPath selectStatement ;

selectStatement : T_SELECT selectElements T_FROM fromClause
                  (T_WHERE whereClause)?
                  (T_GROUP T_BY groupByClause)?
                  (T_ORDER T_BY orderByClause)?
                  (T_LIMIT T_NUMBER)?
                ;

selectElements : selectElement (T_COMMA selectElement)* ;

selectElement : ( T_MULT
                | functionCall
                | caseStatement
                | expression
                ) (T_AS? T_IDENTIFIER)? ;

functionCall : T_IDENTIFIER T_LPAREN T_DISTINCT? selectElements? T_RPAREN ;

caseStatement : T_CASE (T_WHEN expression T_THEN expression)+ (T_ELSE expression)? T_END ;

expression : primaryExpression (operator primaryExpression)* ;

primaryExpression : idPath
                  | T_NUMBER
                  | T_STRING
                  ;

// 新增：专门处理带点的字段/表名路径
idPath : T_IDENTIFIER (T_DOT T_IDENTIFIER)* ;

operator : '=' | '>' | '<' | '>=' | '<=' | '!=' | '<>' ;

// 把 T_QIDENTIFIER 替换成了 idPath
fromClause : T_LPAREN selectStatement T_RPAREN (T_AS? T_IDENTIFIER)?
           | idPath (T_AS? T_IDENTIFIER)? (joinClause)* (T_ON onClause)?
           ;

// 把 T_QIDENTIFIER 替换成了 idPath
joinClause : (T_LEFT | T_RIGHT | T_INNER | T_FULL | T_OUTER)? T_JOIN idPath (T_AS? T_IDENTIFIER)? (T_ON onClause)? ;

onClause : expression (T_AND expression | T_OR expression)* ;

whereClause : expression (T_AND expression | T_OR expression)* ;

groupByClause : selectElements ;

orderByClause : selectElements (T_DESC | T_ASC)? ;

// ==========================================
// Lexer Rules (词法规则)
// ==========================================

T_INSERT      : [iI][nN][sS][eE][rR][tT] ;
T_INTO        : [iI][nN][tT][oO] ;
T_OVERWRITE   : [oO][vV][eE][rR][wW][rR][iI][tT][eE] ;
T_TABLE       : [tT][aA][bB][lL][eE] ;
T_SELECT      : [sS][eE][lL][eE][cC][tT] ;
T_FROM        : [fF][rR][oO][mM] ;
T_JOIN        : [jJ][oO][iI][nN] ;
T_LEFT        : [lL][eE][fF][tT] ;
T_RIGHT       : [rR][iI][gG][hH][tT] ;
T_INNER       : [iI][nN][nN][eE][rR] ;
T_FULL        : [fF][uU][lL][lL] ;
T_OUTER       : [oO][uU][tT][eE][rR] ;
T_ON          : [oO][nN] ;
T_AND         : [aA][nN][dD] ;
T_OR          : [oO][rR] ;
T_WHERE       : [wW][hH][eE][rR][eE] ;
T_GROUP       : [gG][rR][oO][uU][pP] ;
T_BY          : [bB][yY] ;
T_ORDER       : [oO][rR][dD][eE][rR] ;
T_DESC        : [dD][eE][sS][cC] ;
T_ASC         : [aA][sS][cC] ;
T_LIMIT       : [lL][iI][mM][iI][tT] ;
T_AS          : [aA][sS] ;
T_CASE        : [cC][aA][sS][eE] ;
T_WHEN        : [wW][hH][eE][nN] ;
T_THEN        : [tT][hH][eE][nN] ;
T_ELSE        : [eE][lL][sS][eE] ;
T_END         : [eE][nN][dD] ;
T_DISTINCT    : [dD][iI][sS][tT][iI][nN][cC][tT] ;

T_LPAREN      : '(' ;
T_RPAREN      : ')' ;
T_DOT         : '.' ;
T_COMMA       : ',' ;
T_MULT        : '*' ;

T_NUMBER      : '-'? [0-9]+ ('.' [0-9]+)? ;
T_STRING      : '\'' ~[']*? '\'' ;

// 唯一保留的标识符词法，彻底删除了 T_QIDENTIFIER
T_IDENTIFIER  : [a-zA-Z_] [a-zA-Z0-9_]* ;

WS            : [ \n\t\r]+ -> skip ;
COMMENT       : '--' ~[\r\n]* -> skip ;