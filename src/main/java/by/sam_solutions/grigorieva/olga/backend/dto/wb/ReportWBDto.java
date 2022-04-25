package by.sam_solutions.grigorieva.olga.backend.dto.wb;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportWBDto {

    private Integer realizationreport_id;

    private Long suppliercontract_code;

    private Long rrd_id;

    private Long gi_id; 

    private String subject_name;

    private Long nm_id;

    private String brand_name;

    private String sa_name; // наименование

    private String ts_name;

    private String barcode;

    private String doc_type_name;

    private Long quantity;

    private Double retail_price;

    private Double retail_amount;

    private Double sale_percent;

    private Double commission_percent;

    private String office_name;

    private String supplier_oper_name;

    private Timestamp order_dt;

    private Timestamp sale_dt;

    private Timestamp rr_dt;

    private Long shk_id;

    private Double retail_price_withdisc_rub;

    private Long delivery_amount;

    private Long return_amount;

    private Double delivery_rub;

    private String gi_box_type_name;

    private Long product_discount_for_report;

    private Long supplier_promo;

    private Long rid;

   private Double ppvz_spp_prc;

   private Double ppvz_kvw_prc_base;

   private Double ppvz_kvw_prc;

    private Double ppvz_sales_commission;

    private Double ppvz_for_pay;

    private Double ppvz_reward;

    private Double ppvz_vw;

    private Double ppvz_vm_nds;

    private Long ppvz_office_id;

    private String ppvz_office_name;

    private Long ppvz_supplier_id;

}
