package com.skillone.designpatterns.responsibilitychain;

public class Client {

    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 21000, 1);
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("朱校长");
        UniversityApprover universityApprover = new UniversityApprover("Jacky");


        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(universityApprover);
        universityApprover.setApprover(departmentApprover);//需要设置circle

        universityApprover.processRequest(purchaseRequest);//任何人去调用
    }

}
