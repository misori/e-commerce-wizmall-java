package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.ProductEvaluation;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("ProductEvaluationDAO")
@Transactional
public class ProductEvaluationDAOImpl extends SqlMapClientDaoSupport implements ProductEvaluationDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<ProductEvaluation> getProductEvaluationList()
			throws DataAccessException {
		List<ProductEvaluation> productEvaluation = getSqlMapClientTemplate().queryForList("getProductEvaluationList");
        return productEvaluation;
	}

	@SuppressWarnings("unchecked")
	public List<ProductEvaluation> getProductEvaluationListByPid(Integer pid)
			throws DataAccessException {
		List<ProductEvaluation> productEvaluation = getSqlMapClientTemplate().queryForList("getProductEvaluationList");
        return productEvaluation;
	}

	public ProductEvaluation getProductEvaluationListByTid(Integer tid)
			throws DataAccessException {
		ProductEvaluation productEvaluation = (ProductEvaluation)getSqlMapClientTemplate().queryForObject("getProductEvaluationListByTid", tid);
		return productEvaluation;
	}

	public void saveProductEvaluation(ProductEvaluation productEvaluation) {
		getSqlMapClientTemplate().insert("saveProductEvaluation", productEvaluation);

	}

	public void updateProductEvaluation(ProductEvaluation productEvaluation) {
		getSqlMapClientTemplate().update("updateProductEvaluation", productEvaluation);

	}

	public void deleteProductEvaluation(int tid) {
		getSqlMapClientTemplate().delete("deleteProductEvaluation", tid);

	}

	public void deleteProductEvaluationByPid(int pid) {
		getSqlMapClientTemplate().delete("deleteProductEvaluationByPid", pid);

	}


}
