-- ��ʼ�����б�
CREATE DATABASE bookstore CHARACTER SET UTF8;
USE bookstore;
-- ͼ������
CREATE TABLE TYPES(
  id VARCHAR(32) PRIMARY KEY, -- ���
  NAME VARCHAR(50),  -- ��������
  descr VARCHAR(200)  -- ��ע
);

-- ͼ����Ϣ��
CREATE TABLE books(
	id VARCHAR(32) PRIMARY KEY, -- ���
	NAME VARCHAR(50),  -- ͼ������
	price NUMERIC(10,2), -- �۸�
	auth VARCHAR(30), -- ����
	img VARCHAR(100), -- ͼƬ��ַ
	rebate NUMERIC(3,2), -- �ۿ�
	stock INT,   -- ���
	publisher VARCHAR(100), -- ������
	publishdate VARCHAR(19), -- ��������
	pages INT,   -- ҳ��
	size INT,     -- ��������16����
	printtimes INT, -- ӡˢ����
	versions INT,  -- �汾
	brief TEXT,   -- ���
	content TEXT,   -- Ŀ¼
	onlinetime varchar(19) -- ����ʱ��
);

-- ͼ�� -���� ��ϵ��
create table booktype(
   bookid varchar(32) not null,  -- ͼ���ţ������
   typeid varchar(32) not null,  -- �����ţ������
   CONSTRAINT btpk primary key(bookid,typeid), -- ��������
   constraint btfk1 foreign key(bookid) references books(id), -- �������
   constraint btfk2 foreign key(typeid) references types(id)  -- �������
);

-- ����Ա��Ϣ��
CREATE TABLE admins(
  id VARCHAR(32) PRIMARY KEY, -- ���
  NAME VARCHAR(30), -- �û���
  PASSWORD VARCHAR(32), -- ����
  sex CHAR(1),  -- �Ա�
  email VARCHAR(50),  -- ����
  descr VARCHAR(200)  -- ��ע
);

-- ǰ̨�û���
CREATE TABLE users(
	id VARCHAR(32) PRIMARY KEY, -- ���
	NAME VARCHAR(50), -- �û���
	PASSWORD VARCHAR(32), -- ����
	phone VARCHAR(50), -- �ֻ�
	email VARCHAR(50) --  ����
);

-- ������ַ��
CREATE TABLE address(
	id VARCHAR(32) PRIMARY KEY, -- ���
	NAME VARCHAR(50), -- ������ַ
	phone VARCHAR(50), -- ��ϵ�绰
	zip VARCHAR(50), -- �ʱ�
	dft CHAR(1) default '0', -- �Ƿ�����ΪĬ��
	userid VARCHAR(32),  -- �û���ţ������
	mktime varchar(19), -- ����ʱ��
	CONSTRAINT addr_fk1 FOREIGN KEY(userid) REFERENCES users(id) -- �������
);

-- ������
CREATE TABLE orders(
	id VARCHAR(32) PRIMARY 	KEY, -- ���
	userid VARCHAR(32), -- �û���ţ������
	consignee VARCHAR(300), -- �ջ���
	paytype CHAR(1), -- ���ʽ
	amt NUMERIC(10,2), -- �ܽ��
	state CHAR(1), -- ����״̬��0���ȴ�����;1:�̼��ѷ���;2:�����Ѿ����;3:������ȡ��;4:�̻����˻���
	orderdate VARCHAR(19), -- �¶���ʱ��
	CONSTRAINT orders_fk FOREIGN KEY(userid) REFERENCES users(id) -- �������
);

-- ������ϸ��
CREATE TABLE orderline(
	id VARCHAR(32) PRIMARY KEY, -- ���
	orderid VARCHAR(32), -- ������ţ������
	bookid VARCHAR(32), -- ͼ���ţ������
	Amt  INT, -- �ϼ�
	price NUMERIC(10,2), -- �۸�
	CONSTRAINT orderline_fk FOREIGN KEY(orderid) REFERENCES orders(id), -- �������
	CONSTRAINT orderline_fk2 FOREIGN KEY(bookid) REFERENCES books(id) -- �������
);
