package app.mvc.model.service;

import app.mvc.exception.ModifyException;
import app.mvc.exception.NotFoundException;
import app.mvc.model.dao.ProductsDAO;
import app.mvc.model.dao.ProductsDAOImpl;
import app.mvc.model.dto.Products;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    ProductsDAO productsDAO = new ProductsDAOImpl();

    public List<Products> productSelect() throws NotFoundException, SQLException{
        List<Products> list = productsDAO.productSelect();
        if (list.size() == 0) {
            throw new NotFoundException("상품이 없습니다 다음을 기약해주세요 ㅠㅠ");
        }
        return list;
    }

    public Products productSelectByProductId(int product_id) throws NotFoundException, SQLException{
       Products products = productsDAO.productSelectByProductId(product_id);
        if (products == null) {
            throw new NotFoundException("상품번호 " + product_id + "에 해당하는 상품이 없습니다");
        }
        return products;
    }

    public int productUpdateByProductId(Products products) throws ModifyException,SQLException {
        int result =0;
        result = productsDAO.productUpdateByProductId(products);
        if(result == 0) throw new ModifyException("상품이 수정되지 않았습니다");
        return result;
    }

    public int productDeleteByProductId(int product_id) throws ModifyException , SQLException{
        int result =0;
        result = productsDAO.productDeleteByProductId(product_id);
        if(result == 0) throw new ModifyException("상품이 삭제되지 않았습니다");
        return result;
    }

    public List<Products> productSelectByCategory(int categorySeq) throws NotFoundException,SQLException{
        List<Products> list = productsDAO.productSelectByCategory(categorySeq);
        if (list.size() == 0) {
            throw new NotFoundException("카테고리 번호를 확인해주세요");
        }
        return list;
    }

    public int productInsert(Products products) throws ModifyException , SQLException{
        int result =0;
        result = productsDAO.productInsert(products);
        if(result == 0) throw new ModifyException("상품이 등록되지 않았습니다");
        return result;
    }

    public List<Products> productSelectRec() throws NotFoundException, SQLException{
        List<Products> list = productsDAO.productSelectRec();
        if (list.size() == 0) {
            throw new NotFoundException("상품이 없습니다 다음을 기약해주세요 ㅠㅠ");
        }
        return list;
    }
    
    public List<Products> productSelectUserRec(String userId) throws NotFoundException, SQLException{
        List<Products> list = productsDAO.productSelectUserRec(userId);
        if (list.size() == 0) {
            throw new NotFoundException("상품이 없습니다 다음을 기약해주세요 ㅠㅠ");
        }
        return list;
    }
    
    public int BlockChk(int productId) throws NotFoundException, SQLException{
    	int chk =productsDAO.blockChk(productId);
    	return chk;
    }

	public void productUpdateBlock(int id, int chk) throws NotFoundException, SQLException{
		productsDAO.productUpdateBlock(id,chk);
	}


}
