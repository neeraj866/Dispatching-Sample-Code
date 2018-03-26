package com.demoapp.model_classes;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by android5 on 25/6/16.
 */
public class JobListingResponse implements Serializable {

    /**
     * feilds comming in the API
     */
    private String off_material;
    private String off_bill_customer;
    private String from_contact_number;
    private String on_bill_customer;
    private String on_haul_unit;
    private String from_longitude;
    private String off_unit_needqty;
    private String date;
    private String type;
    private String on_material;
    private String on_pay_backhaul;
    private String time;
    private String off_haul_unit;
    private String order_num;
    private String off_notes;
    private String off_customer_po;
    private String backhaul_order_num;
    private String on_notes;
    private String on_unit;
    private String from_contact_name;
    private String on_pay;
    private String on_haul_unit_needqty;
    private String off_haul_unit_needqty;
    private String off_pay_backhaul;
    private String to_contact_name;
    private String off_pay;
    private String on_customer_po;
    private String to_latitude;
    private String on_unit_needqty;
    private String from_latitude;
    private String trailer_type_yards;
    private String job_name;
    private String to_address;
    private String from_address;
    private String trailer_type;
    private String off_quantity_remaining;
    private String on_quantity_remaining;
    private String off_unit;
    private String to_longitude;
    private String to_contact_number;
    private String trailer_type_tons;
    private String off_customer;
    private String on_customer;
    private String from_supplier;
    private String to_supplier;
    private boolean is_today;
    private String current_load;
    private String status;
    private String off_job_name;
    private String on_job_name;
    private String on_need_ticket;
    private String off_need_ticket;
    private String on_signatory_code;
    private String off_signatory_code;
    private String day_start_time;
    private String day_num_loads;
    private String off_break_time;
    private String on_break_time;

    /**
     * Local feilds
     */
    private String off_ticket_number;
    private String ticketPath;
    private String load_num;
    private String end_time;
    private String end_latitude;
    private String end_longitude;
    private String note;
    private String on_signatory;
    private String off_signatory;
    private String reject_reason;
    private String on_customer_signature;
    private String off_customer_signature;
    private String driver_signature;
    private String total_time;
    private String start_time;
    private String on_ticket_number;
    private int off_rating;
    private int on_rating;
    private boolean complete;
    private boolean customer_present_haul_on;
    private boolean customer_present_haul_off;
    private double start_latitude;
    private double start_longitude;
    private double on_quantity;
    private double off_quantity;
    private double on_haul_quantity;
    private double off_haul_quantity;
    private double cuttent_latitide;
    private double cuttent_longitude;
    private int jobStatus;
    private String firstJobTime;
    private String breakTime;
    private boolean isLastJob;
    private boolean isHaulOnQrCodeScanned;
    private boolean isHaulOffQrCodeScanned;
    private String onStartQuantity;

    /**
     * offline management
     */
    private boolean againHitComplete; //  this is used for hourly job to get again hit complete with true
    private boolean isStart; //  this will use when data is stored in offline mode to check do we need to hit the start api
    private boolean isOffSignauture; //  this will use when data is stored in offline mode to check do we need to upload haul off/ dirtdeal first signatures
    private boolean isOnSignauture; //  this will use when data is stored in offline mode to check do we need to upload haul on/dirt deal second/onsite/wait signatures
    private boolean isPitTicket; //  this will use when data is stored in offline mode to check do we need to upload pit ticket
    private boolean isDumpTicket; //  this will use when data is stored in offline mode to check do we need to upload dump ticket
    private boolean isHitCompleted; //  this will use when data is stored in offline mode to check do we need to hit the complete api
    private boolean isLeavingJob; //  this will use when data is stored in offline mode to check leaving job functionality is done in onsite/wait
    private boolean isGetSignature; //  this will use when data is stored in offline mode to check signature screen has been done in haul on/dirtdeal second signatures
    private boolean isCancel;//  this will use when data is stored in offline mode to check is user canceled the job
    private boolean onceCompleted;// This will check hourly job once completed or not

    public boolean isGetSignature() {
        return isGetSignature;
    }

    public void setGetSignature(boolean getSignature) {
        isGetSignature = getSignature;
    }

    public boolean isLeavingJob() {
        return isLeavingJob;
    }

    public void setLeavingJob(boolean leavingJob) {
        isLeavingJob = leavingJob;
    }

    public boolean isOnceCompleted() {
        return onceCompleted;
    }

    public void setOnceCompleted(boolean onceCompleted) {
        this.onceCompleted = onceCompleted;
    }

    public boolean isAgainHitComplete() {
        return againHitComplete;
    }

    public void setAgainHitComplete(boolean againHitComplete) {
        this.againHitComplete = againHitComplete;
    }

    public String getCurrent_load() {
        return current_load;
    }

    public void setCurrent_load(String current_load) {
        this.current_load = current_load;
    }

    public String getOff_break_time() {
        return off_break_time;
    }

    public void setOff_break_time(String off_break_time) {
        this.off_break_time = off_break_time;
    }

    public String getOn_break_time() {
        return on_break_time;
    }

    public void setOn_break_time(String on_break_time) {
        this.on_break_time = on_break_time;
    }

    private ArrayList<CheckpointModel> checkpointModels = new ArrayList<>();

    public String getDay_start_time() {
        return day_start_time;
    }

    public void setDay_start_time(String day_start_time) {
        this.day_start_time = day_start_time;
    }

    public String getDay_num_loads() {
        return day_num_loads;
    }

    public void setDay_num_loads(String day_num_loads) {
        this.day_num_loads = day_num_loads;
    }

    public ArrayList<CheckpointModel> getCheckpointModels() {
        return checkpointModels;
    }

    public void setCheckpointModels(ArrayList<CheckpointModel> checkpointModels) {
        this.checkpointModels = checkpointModels;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public String getOnStartQuantity() {
        return onStartQuantity;
    }

    public void setOnStartQuantity(String onStartQuantity) {
        this.onStartQuantity = onStartQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOffSignauture() {
        return isOffSignauture;
    }

    public void setIsOffSignauture(boolean isOffSignauture) {
        this.isOffSignauture = isOffSignauture;
    }

    public boolean isOnSignauture() {
        return isOnSignauture;
    }

    public void setIsOnSignauture(boolean isOnSignauture) {
        this.isOnSignauture = isOnSignauture;
    }

    public boolean isPitTicket() {
        return isPitTicket;
    }

    public void setIsPitTicket(boolean isPitTicket) {
        this.isPitTicket = isPitTicket;
    }

    public boolean isDumpTicket() {
        return isDumpTicket;
    }

    public void setIsDumpTicket(boolean isDumpTicket) {
        this.isDumpTicket = isDumpTicket;
    }

    public boolean isHitCompleted() {
        return isHitCompleted;
    }

    public void setIsHitCompleted(boolean isHitCompleted) {
        this.isHitCompleted = isHitCompleted;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isHaulOnQrCodeScanned() {
        return isHaulOnQrCodeScanned;
    }

    public void setIsHaulOnQrCodeScanned(boolean isHaulOnQrCodeScanned) {
        this.isHaulOnQrCodeScanned = isHaulOnQrCodeScanned;
    }

    public boolean isHaulOffQrCodeScanned() {
        return isHaulOffQrCodeScanned;
    }

    public void setIsHaulOffQrCodeScanned(boolean isHaulOffQrCodeScanned) {
        this.isHaulOffQrCodeScanned = isHaulOffQrCodeScanned;
    }

    public String getOn_signatory_code() {
        return on_signatory_code;
    }

    public void setOn_signatory_code(String on_signatory_code) {
        this.on_signatory_code = on_signatory_code;
    }

    public String getOff_signatory_code() {
        return off_signatory_code;
    }

    public void setOff_signatory_code(String off_signatory_code) {
        this.off_signatory_code = off_signatory_code;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public boolean isLastJob() {
        return isLastJob;
    }

    public void setIsLastJob(boolean isLastJob) {
        this.isLastJob = isLastJob;
    }

    public String getOn_need_ticket() {
        return on_need_ticket;
    }

    public void setOn_need_ticket(String on_need_ticket) {
        this.on_need_ticket = on_need_ticket;
    }

    public String getOff_need_ticket() {
        return off_need_ticket;
    }

    public void setOff_need_ticket(String off_need_ticket) {
        this.off_need_ticket = off_need_ticket;
    }

    public String getOff_job_name() {
        return off_job_name;
    }

    public void setOff_job_name(String off_job_name) {
        this.off_job_name = off_job_name;
    }

    public String getOn_job_name() {
        return on_job_name;
    }

    public void setOn_job_name(String on_job_name) {
        this.on_job_name = on_job_name;
    }

    public String getFirstJobTime() {
        return firstJobTime;
    }


    public void setFirstJobTime(String firstJobTime) {
        this.firstJobTime = firstJobTime;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getOn_customer_signature() {
        return on_customer_signature;
    }

    public void setOn_customer_signature(String on_customer_signature) {
        this.on_customer_signature = on_customer_signature;
    }

    public String getOff_customer_signature() {
        return off_customer_signature;
    }

    public void setOff_customer_signature(String off_customer_signature) {
        this.off_customer_signature = off_customer_signature;
    }

    public String getLoad_num() {
        return load_num;
    }

    public void setLoad_num(String load_num) {
        this.load_num = load_num;
    }

    public boolean is_today() {
        return is_today;
    }

    public void setIs_today(boolean is_today) {
        this.is_today = is_today;
    }

    public String getOff_material() {
        return off_material;
    }

    public void setOff_material(String off_material) {
        this.off_material = off_material;
    }

    public String getOff_bill_customer() {
        return off_bill_customer;
    }

    public void setOff_bill_customer(String off_bill_customer) {
        this.off_bill_customer = off_bill_customer;
    }

    public String getFrom_contact_number() {
        return from_contact_number;
    }

    public void setFrom_contact_number(String from_contact_number) {
        this.from_contact_number = from_contact_number;
    }

    public String getOn_bill_customer() {
        return on_bill_customer;
    }

    public void setOn_bill_customer(String on_bill_customer) {
        this.on_bill_customer = on_bill_customer;
    }

    public String getOn_haul_unit() {
        return on_haul_unit;
    }

    public void setOn_haul_unit(String on_haul_unit) {
        this.on_haul_unit = on_haul_unit;
    }

    public String getFrom_longitude() {
        return from_longitude;
    }

    public void setFrom_longitude(String from_longitude) {
        this.from_longitude = from_longitude;
    }

    public String getOff_unit_needqty() {
        return off_unit_needqty;
    }

    public void setOff_unit_needqty(String off_unit_needqty) {
        this.off_unit_needqty = off_unit_needqty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOn_material() {
        return on_material;
    }

    public void setOn_material(String on_material) {
        this.on_material = on_material;
    }

    public String getOn_pay_backhaul() {
        return on_pay_backhaul;
    }

    public void setOn_pay_backhaul(String on_pay_backhaul) {
        this.on_pay_backhaul = on_pay_backhaul;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOff_haul_unit() {
        return off_haul_unit;
    }

    public void setOff_haul_unit(String off_haul_unit) {
        this.off_haul_unit = off_haul_unit;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOff_notes() {
        return off_notes;
    }

    public void setOff_notes(String off_notes) {
        this.off_notes = off_notes;
    }

    public String getOff_customer_po() {
        return off_customer_po;
    }

    public void setOff_customer_po(String off_customer_po) {
        this.off_customer_po = off_customer_po;
    }

    public String getBackhaul_order_num() {
        return backhaul_order_num;
    }

    public void setBackhaul_order_num(String backhaul_order_num) {
        this.backhaul_order_num = backhaul_order_num;
    }

    public String getOn_notes() {
        return on_notes;
    }

    public void setOn_notes(String on_notes) {
        this.on_notes = on_notes;
    }

    public String getOn_unit() {
        return on_unit;
    }

    public void setOn_unit(String on_unit) {
        this.on_unit = on_unit;
    }

    public String getFrom_contact_name() {
        return from_contact_name;
    }

    public void setFrom_contact_name(String from_contact_name) {
        this.from_contact_name = from_contact_name;
    }

    public String getOn_pay() {
        return on_pay;
    }

    public void setOn_pay(String on_pay) {
        this.on_pay = on_pay;
    }

    public String getOn_haul_unit_needqty() {
        return on_haul_unit_needqty;
    }

    public void setOn_haul_unit_needqty(String on_haul_unit_needqty) {
        this.on_haul_unit_needqty = on_haul_unit_needqty;
    }

    public String getOff_haul_unit_needqty() {
        return off_haul_unit_needqty;
    }

    public void setOff_haul_unit_needqty(String off_haul_unit_needqty) {
        this.off_haul_unit_needqty = off_haul_unit_needqty;
    }

    public String getOff_pay_backhaul() {
        return off_pay_backhaul;
    }

    public void setOff_pay_backhaul(String off_pay_backhaul) {
        this.off_pay_backhaul = off_pay_backhaul;
    }

    public String getTo_contact_name() {
        return to_contact_name;
    }

    public void setTo_contact_name(String to_contact_name) {
        this.to_contact_name = to_contact_name;
    }

    public String getOff_pay() {
        return off_pay;
    }

    public void setOff_pay(String off_pay) {
        this.off_pay = off_pay;
    }

    public String getOn_customer_po() {
        return on_customer_po;
    }

    public void setOn_customer_po(String on_customer_po) {
        this.on_customer_po = on_customer_po;
    }

    public String getTo_latitude() {
        return to_latitude;
    }

    public void setTo_latitude(String to_latitude) {
        this.to_latitude = to_latitude;
    }

    public String getOn_unit_needqty() {
        return on_unit_needqty;
    }

    public void setOn_unit_needqty(String on_unit_needqty) {
        this.on_unit_needqty = on_unit_needqty;
    }

    public String getFrom_latitude() {
        return from_latitude;
    }

    public void setFrom_latitude(String from_latitude) {
        this.from_latitude = from_latitude;
    }

    public String getTrailer_type_yards() {
        return trailer_type_yards;
    }

    public void setTrailer_type_yards(String trailer_type_yards) {
        this.trailer_type_yards = trailer_type_yards;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public String getTrailer_type() {
        return trailer_type;
    }

    public void setTrailer_type(String trailer_type) {
        this.trailer_type = trailer_type;
    }

    public String getOff_quantity_remaining() {
        return off_quantity_remaining;
    }

    public void setOff_quantity_remaining(String off_quantity_remaining) {
        this.off_quantity_remaining = off_quantity_remaining;
    }

    public String getOn_quantity_remaining() {
        return on_quantity_remaining;
    }

    public void setOn_quantity_remaining(String on_quantity_remaining) {
        this.on_quantity_remaining = on_quantity_remaining;
    }

    public String getOff_unit() {
        return off_unit;
    }

    public void setOff_unit(String off_unit) {
        this.off_unit = off_unit;
    }

    public String getTo_longitude() {
        return to_longitude;
    }

    public void setTo_longitude(String to_longitude) {
        this.to_longitude = to_longitude;
    }

    public String getTo_contact_number() {
        return to_contact_number;
    }

    public void setTo_contact_number(String to_contact_number) {
        this.to_contact_number = to_contact_number;
    }

    public String getTrailer_type_tons() {
        return trailer_type_tons;
    }

    public void setTrailer_type_tons(String trailer_type_tons) {
        this.trailer_type_tons = trailer_type_tons;
    }

    public String getOff_customer() {
        return off_customer;
    }

    public void setOff_customer(String off_customer) {
        this.off_customer = off_customer;
    }

    public String getOn_customer() {
        return on_customer;
    }

    public void setOn_customer(String on_customer) {
        this.on_customer = on_customer;
    }

    public String getFrom_supplier() {
        return from_supplier;
    }

    public void setFrom_supplier(String from_supplier) {
        this.from_supplier = from_supplier;
    }

    public String getTo_supplier() {
        return to_supplier;
    }

    public void setTo_supplier(String to_supplier) {
        this.to_supplier = to_supplier;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public double getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(double start_latitude) {
        this.start_latitude = start_latitude;
    }

    public double getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(double start_longitude) {
        this.start_longitude = start_longitude;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_latitude() {
        return end_latitude;
    }

    public void setEnd_latitude(String end_latitude) {
        this.end_latitude = end_latitude;
    }

    public String getEnd_longitude() {
        return end_longitude;
    }

    public void setEnd_longitude(String end_longitude) {
        this.end_longitude = end_longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOn_signatory() {
        return on_signatory;
    }

    public void setOn_signatory(String on_signatory) {
        this.on_signatory = on_signatory;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public int getOn_rating() {
        return on_rating;
    }

    public void setOn_rating(int on_rating) {
        this.on_rating = on_rating;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isCustomer_present_haul_on() {
        return customer_present_haul_on;
    }

    public void setCustomer_present_haul_on(boolean customer_present_haul_on) {
        this.customer_present_haul_on = customer_present_haul_on;
    }

    public boolean isCustomer_present_haul_off() {
        return customer_present_haul_off;
    }

    public void setCustomer_present_haul_off(boolean customer_present_haul_off) {
        this.customer_present_haul_off = customer_present_haul_off;
    }

    public double getOn_quantity() {
        return on_quantity;
    }

    public void setOn_quantity(double on_quantity) {
        this.on_quantity = on_quantity;
    }

    public double getOn_haul_quantity() {
        return on_haul_quantity;
    }

    public void setOn_haul_quantity(double on_haul_quantity) {
        this.on_haul_quantity = on_haul_quantity;
    }

    public String getOn_ticket_number() {
        return on_ticket_number;
    }

    public void setOn_ticket_number(String on_ticket_number) {
        this.on_ticket_number = on_ticket_number;
    }

    public String getOff_ticket_number() {
        return off_ticket_number;
    }

    public void setOff_ticket_number(String off_ticket_number) {
        this.off_ticket_number = off_ticket_number;
    }

    public String getTicketPath() {
        return ticketPath;
    }

    public void setTicketPath(String ticketPath) {
        this.ticketPath = ticketPath;
    }

    public String getDriver_signature() {
        return driver_signature;
    }

    public void setDriver_signature(String driver_signature) {
        this.driver_signature = driver_signature;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public double getOff_quantity() {
        return off_quantity;
    }

    public void setOff_quantity(double off_quantity) {
        this.off_quantity = off_quantity;
    }

    public double getOff_haul_quantity() {
        return off_haul_quantity;
    }

    public void setOff_haul_quantity(double off_haul_quantity) {
        this.off_haul_quantity = off_haul_quantity;
    }

    public String getOff_signatory() {
        return off_signatory;
    }

    public void setOff_signatory(String off_signatory) {
        this.off_signatory = off_signatory;
    }

    public int getOff_rating() {
        return off_rating;
    }

    public void setOff_rating(int off_rating) {
        this.off_rating = off_rating;
    }

    public double getCuttent_latitide() {
        return cuttent_latitide;
    }

    public void setCuttent_latitide(double cuttent_latitide) {
        this.cuttent_latitide = cuttent_latitide;
    }

    public double getCuttent_longitude() {
        return cuttent_longitude;
    }

    public void setCuttent_longitude(double cuttent_longitude) {
        this.cuttent_longitude = cuttent_longitude;
    }
}
