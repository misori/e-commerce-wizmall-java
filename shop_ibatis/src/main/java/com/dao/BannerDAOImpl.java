package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Banner;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("BannerDAO")
@Transactional
public class BannerDAOImpl extends SqlMapClientDaoSupport implements BannerDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<Banner> getAllBannerList() throws DataAccessException {
		List<Banner> banner = getSqlMapClientTemplate().queryForList("getAllBannerList");
        return banner;
	}

	public Banner getBannerByTid(Integer tid) throws DataAccessException {
		Banner banner = (Banner)getSqlMapClientTemplate().queryForObject("getBannerByTid", tid);
		return banner;
	}

	public void deleteBanner(int tid) {
		getSqlMapClientTemplate().delete("deleteBanner", tid);

	}

	public void saveBanner(Banner banner) {
		getSqlMapClientTemplate().insert("saveBanner", banner);

	}

	public void updateBanner(Banner banner) {
		getSqlMapClientTemplate().update("updateBanner", banner);

	}

}
