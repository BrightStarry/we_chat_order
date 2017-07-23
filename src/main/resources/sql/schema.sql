-- 商品表
CREATE TABLE product_info(
  product_id VARCHAR(32) NOT NULL,
  product_name VARCHAR(64) NOT NULL COMMENT '商品名',
  product_price DECIMAL(8,2) NOT NULL COMMENT '单价',
  product_stock INT NOT NULL COMMENT  '库存',
  product_description VARCHAR(64) COMMENT '描述',
  product_icon VARCHAR(512) COMMENT '小图',
  category_type INT NOT NULL COMMENT '类目编号',
  product_status TINYINT(1) DEFAULT 0 COMMENT '商品状态， 0正常，1下架',
  -- MySQL5.7+ 才可以使用两个日期为current_timestamp类型的 TIMESTAMP
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp COMMENT '修改时间',

  PRIMARY KEY (product_id)
)COMMENT '商品表';

-- 类目表
CREATE TABLE product_category(
  category_id INT NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(64) NOT NULL COMMENT '类目名字',
  category_type INT NOT NULL COMMENT '类目编号',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp COMMENT '修改时间',

  PRIMARY KEY (category_id),
  UNIQUE KEY uqe_category_type(category_type)
)COMMENT '类目表';

-- 订单表
CREATE TABLE order_master(
  order_id VARCHAR(32) NOT NULL ,
  buyer_name VARCHAR(32) NOT NULL COMMENT '买家姓名',
  buyer_phone VARCHAR(32) NOT NULL COMMENT '买家电话',
  buyer_address VARCHAR(128) NOT NULL COMMENT '买家地址',
  buyer_openid VARCHAR(64) NOT NULL COMMENT '买家微信openid',
  order_amount DECIMAL(8,2) NOT NULL COMMENT '订单总金额',
  order_status TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单',
  pay_status TINYINT(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp COMMENT '修改时间',

  PRIMARY KEY (order_id),
  KEY idx_buyer_openid(buyer_openid)
)COMMENT '订单表';

-- 订单详情表
CREATE TABLE order_detail(
  detail_id VARCHAR(32) NOT NULL ,
  order_id VARCHAR(32) NOT NULL COMMENT '订单id',
  product_id VARCHAR(32) NOT NULL COMMENT '商品id',
  product_name VARCHAR(64) NOT NULL COMMENT '商品名称',
  product_price DECIMAL(8,2) NOT NULL COMMENT '商品价格',
  product_quantity INT NOT NULL COMMENT '商品数量',
  product_icon VARCHAR(512) COMMENT '商品小图',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  update_time TIMESTAMP NOT NULL DEFAULT current_timestamp
  ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (detail_id),
  KEY idx_order_id(order_id)
)COMMENT '订单详情表';




























