package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.ProductImage;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("ProductImageDAO")
@Transactional
public class ProductImageDAOImpl extends SqlMapClientDaoSupport implements ProductImageDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	/**
	 *(non-Javadoc)
	 *제품별 이미지 가져오기
	 */
	@SuppressWarnings("unchecked")
	public List<ProductImage> getProductImageByProduct(int pid)
			throws DataAccessException {
		List<ProductImage> banner = getSqlMapClientTemplate().queryForList("getProductImageByProduct");
        return banner;
	}

	public ProductImage getProductImageByTid(int tid){

		ProductImage banner = (ProductImage)getSqlMapClientTemplate().queryForObject("getProductImageByTid", tid);
		return banner;
	}

	public void deleteProductImage(int tid) {
		getSqlMapClientTemplate().delete("deleteProductImage", tid);

	}

	public void saveProductImage(ProductImage productImage) {
		getSqlMapClientTemplate().insert("saveProductImage", productImage);

	}

	public void updateProductImage(ProductImage productImage) {
		getSqlMapClientTemplate().update("updateProductImage", productImage);

	}

}
