package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardFile;
import com.domain.Category;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("CategoryDAO")
@Transactional
public class CategoryDAOImpl extends SqlMapClientDaoSupport implements CategoryDAO {

	//@Autowired
	//private SqlMapClient sqlMapClient;
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategoryList() {
		// TODO Auto-generated method stub
		//System.out.println("getAllCategoryList");
		List<Category> category = getSqlMapClientTemplate().queryForList("getAllCategoryList");
        return category;
	}


	@SuppressWarnings("unchecked")
	public List<Category> getCategoryPerLength(Integer length) {
		// TODO Auto-generated method stub
		List<Category> category = getSqlMapClientTemplate().queryForList("getCategoryPerLength", length);

        return category;
	}
	/**
	 * Instantiates a new CagegoryDAOImpl
	 *
	 */
	public CategoryDAOImpl() { // 이부분에 기본에서 추가되는 부분이다.
		super();
	}





	/**
	 * JPQL Query - getAllCategoryList
	 * 특정 카테고리의 바로 하위 카테고리 리스트를 가져온다.
*/

	@SuppressWarnings("unchecked")
	public List<Category> getCategoryList(HashMap<String, String> params) throws DataAccessException{
		//System.out.println("getCategoryList:"+params);
		List<Category> category = getSqlMapClientTemplate().queryForList("getCategoryList", params);
        return category;
	}





	/**
	 * 현재카테고리에서 최대 순서값 가져오기(non-Javadoc)
	 *
*/
	public Integer getMaxCatOrder(Integer comlen, String ccode, Integer strlen) throws DataAccessException {
		//Long strlen_long	= strlen.longValue();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("comlen",Integer.toString(comlen));
		map.put("ccode",ccode);
		map.put("strlen",Integer.toString(strlen));
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getMaxCatOrder", map));//.intValue()
		cnt	= cnt == null ? 0 : cnt;
        return cnt;
	}

	public String getMaxCatno(Integer comlen, String ccode, Integer strlen) throws DataAccessException {
		//Long strlen_long	= strlen.longValue();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("comlen",Integer.toString(comlen));
		map.put("ccode",ccode);
		map.put("strlen",Integer.toString(strlen));
		//System.out.println("CategoryDaoImpl : getMaxCatno");
		//System.out.println(map);
		String rtn = (String)getSqlMapClientTemplate().queryForObject("getMaxCatno", map);
		//if (rtn == null) rtn = "001";
		//System.out.println("rtn : " +rtn);
		return rtn;
	}

	/**
	 * (non-Javadoc)
	 * @see com.dao.BoardDAO#getBoardByPrimaryKey(java.lang.Integer)
*/
	public Category getCategoryByPrimaryKey(Integer tid) throws DataAccessException {
		Category category = (Category)getSqlMapClientTemplate().queryForObject("getCategoryByPrimaryKey", tid);
        return category;
	}

	public void deleteCategory(String cat_no) {
		getSqlMapClientTemplate().delete("deleteCategory", cat_no);

	}

	public void deleteCategory(Category category) {
		getSqlMapClientTemplate().delete("deleteCategory", category);

	}

	public void saveCategory(Category category) {
		getSqlMapClientTemplate().insert("saveCategory", category);

	}

	public void updateCategory(Category category) {
		getSqlMapClientTemplate().update("updateCategory", category);

	}

	public List<Category> getCatListByCatNo(String cat_no)
			throws DataAccessException {
		List<Category> category = getSqlMapClientTemplate().queryForList("getCatListByCatNo", cat_no);
        return category;
	}


}
