package gz.itcast.util;

import java.util.List;

/**
 * ��ҳ�������ڴ洢��ǰҳ��ҳ��ص�����
 * 
 * @author APPle
 * 
 */
public class PageBean<T> {
	List<T> data; // ��ǰҳ����
	int firstPage; // ��ҳ
	int prePage; // ��һҳ
	int nextPage;// ��һҳ
	int totalPage;// ĩҳ/��ҳ��
	int currentPage;// ��ǰҳ
	int totalCount;// �ܼ�¼��
	int pageSize;// ÿҳ��ʾ��¼��
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * ��ҳ
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	/**
	 * ��һҳ �㷨�������ǰҳ����ҳ����Ϊ1������Ϊ����ǰҳ-1��
	 * @return
	 */
	public int getPrePage() {
		return this.getCurrentPage()==this.getFirstPage()
					?1:
					this.getCurrentPage()-1;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	
	/**
	 * ��һҳ  �㷨�� �����ǰҳ��ĩҳ����Ϊĩҳ������Ϊ����ǰҳ+1��
	 * @return
	 */
	public int getNextPage() {
		return this.getCurrentPage()==this.getTotalPage()
				? this.getTotalPage()
				: this.getCurrentPage()+1;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	/**
	 * ��ҳ��  �㷨��  ���   �ܼ�¼��%ÿҳ��ʾ��¼���ܹ����� ,��Ϊ���ܼ�¼��/ÿҳ��ʾ��¼����������  ���ܼ�¼��/ÿҳ��ʾ��¼��+1��  
	 * @return
	 */
	public int getTotalPage() {
		return this.getTotalCount()%this.getPageSize()==0
				? this.getTotalCount()/this.getPageSize()
				: this.getTotalCount()/this.getPageSize()+1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
