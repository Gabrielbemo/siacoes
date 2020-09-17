package br.edu.utfpr.dv.siacoes.dao;

import br.edu.utfpr.dv.siacoes.model.ActivityUnit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoModel<T> {
    protected Connection conn = null;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    protected String queryStringList;

    protected abstract T loadObject(ResultSet rs) throws SQLException;

    protected void setQueryListString(String rs){
        this.queryStringList = rs;
    }

    protected String getQueryStringList(){
        return this.queryStringList;
    }

    protected void closeConnections(){
        if((rs != null) && !rs.isClosed())
            rs.close();
        if((stmt != null) && !stmt.isClosed())
            stmt.close();
        if((conn != null) && !conn.isClosed())
            conn.close();
    }

    public List<T> listAll(String query) throws SQLException {
        conn = null;
        stmt = null;
        rs = null;

        setQueryListString(query);

        try{
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(getQueryStringList());

            List<T> list = new ArrayList<T>();

            while(rs.next()){
                list.add(this.loadObject(rs));
            }

            return list;
        }finally{
            closeConnections();
        }
    }


}
