package com.lcpa.lclove.vo;

import java.util.Map;

/**
 * Created by shaoheng.huang on 2016/12/28.
 */
public class QueryParameter {
    private Paging paging;

    private Map queryPara;

    public QueryParameter(Paging paging, Map queryPara){
        this.paging = paging;
        this.queryPara = queryPara;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Map getQueryPara() {
        return queryPara;
    }

    public void setQueryPara(Map queryPara) {
        this.queryPara = queryPara;
    }
}
