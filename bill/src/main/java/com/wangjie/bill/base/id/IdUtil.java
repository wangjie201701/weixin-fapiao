package com.wangjie.bill.base.id;

import java.util.Random;
import java.util.UUID;


public final class IdUtil {

    private static final String[] PREFIXED = new String[]{
            "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AI", "AJ",
            "BA", "BB", "BC", "BD", "BE", "BF", "BG", "BH", "BI", "BJ",
            "CA", "CB", "CC", "CD", "CE", "CF", "CG", "CH", "CI", "CJ",
            "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH", "DI", "DJ",
            "EA", "EB", "EC", "ED", "EE", "EF", "EG", "EH", "EI", "EJ",
            "FA", "FB", "FC", "FD", "FE", "FF", "FG", "FH", "FI", "FJ",
            "GA", "GB", "GC", "GD", "GE", "GF", "GG", "GH", "GI", "GJ",
            "HA", "HB", "HC", "HD", "HE", "HF", "HG", "HH", "HI", "HJ",
            "IA", "IB", "IC", "ID", "IE", "IF", "IG", "IH", "II", "IJ",
            "JA", "JB", "JC", "JD", "JE", "JF", "JG", "JH", "JI", "JJ"
    };

    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static final Random RANDOM = new Random();

    public static String userId() {
        return get(Ids.USER_ID);
    }

    public static String userAccessId() {
        return get(Ids.USER_ASSET_ID);
    }

    public static String memberId() {
        return get(Ids.MEMBER_ID);
    }

    public static String brandId() {
        return get(Ids.BRAND_ID);
    }

    public static String brandUserId() {
        return get(Ids.BRAND_USER_ID);
    }

    public static String brandEmployeeUserId() {
        return get(Ids.BRAND_EMPLOYEE_USER_ID);
    }

    public static String brandRoleId() {
        return get(Ids.BRAND_ROLE_ID);
    }

    public static String brandStoreId() {
        return get(Ids.BRAND_STORE_ID);
    }

    public static String orderId() {
        return get(Ids.ORDER_ID);
    }

    public static String activityId() {
        return get(Ids.ACTIVITY_ID);
    }

    public static String supplierId() {
        return get(Ids.SUPPLIER_ID);
    }

    public static String supplierTypeId() {
        return get(Ids.SUPPLIER_TYPE_ID);
    }

    public static String supplierGoodsId() {
        return get(Ids.SUPPLIER_GOODS_ID);
    }

    public static String orgId() {
        return get(Ids.ORG_ID);
    }

    public static String orderGoodsId() {
        return get(Ids.ORDER_GOODS_ID);
    }

    public static String productId() {
        return get(Ids.PRODUCT_ID);
    }

    public static String assetCardsId() {
        return get(Ids.ASSET_CARDS_ID);
    }

    public static String assetCardsItemId() {
        return get(Ids.ASSET_CARDS_ITEM_ID);
    }

    public static String productImageId() {
        return get(Ids.PRODUCT_IMAGE_ID);
    }

    public static String cardGroupId() {
        return get(Ids.CARD_GROUP_ID);
    }

    public static String cardId() {
        return get(Ids.CARD_ID);
    }

    public static String consumptionId() {
        return get(Ids.CONSUMPTION_ID);
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static String get(Ids id) {
        return get(id.ordinal());
    }

    private static String get(int v) {
        return String.format("%s%s", PREFIXED[v], uuid()).toUpperCase();
    }

    /**
     * 通过枚举顺序下标控制, 新增请加最后面
     */
    private enum Ids {
        /**
         * 用户编号
         */
        USER_ID,
        /**
         * 用户资产编号
         */
        USER_ASSET_ID,
        /**
         * 会员编号
         */
        MEMBER_ID,
        /**
         * 品牌编号
         */
        BRAND_ID,
        /**
         * 品牌用户编号
         */
        BRAND_USER_ID,
        /**
         * 员工编号
         */
        BRAND_EMPLOYEE_USER_ID,
        /**
         * 角色编号
         */
        BRAND_ROLE_ID,
        /**
         * 门店编号
         */
        BRAND_STORE_ID,
        /**
         * 订单编号
         */
        ORDER_ID,
        /**
         * 活动编号
         */
        ACTIVITY_ID,
        /**
         * 供应商编号
         */
        SUPPLIER_ID,
        /**
         * 供应商商品
         */
        SUPPLIER_GOODS_ID,
        /**
         * 供应商类别编号
         */
        SUPPLIER_TYPE_ID,
        /**
         * 组织编号
         */
        ORG_ID,
        /**
         * 订单商品业务ID
         */
        ORDER_GOODS_ID,
        /**
         * 商品业务ID
         */
        PRODUCT_ID,
        /**
         * 资产卡业务ID
         */
        ASSET_CARDS_ID,
        /**
         * 资产卡条目业务ID
         */
        ASSET_CARDS_ITEM_ID,
        /**
         * 商品图片ID
         */
        PRODUCT_IMAGE_ID,
        /**
         * 卡组ID
         */
        CARD_GROUP_ID,
        /**
         * 卡组ID
         */
        CARD_ID,
        /**
         * 卡操作记录ID
         */
        CONSUMPTION_ID
    }
}
