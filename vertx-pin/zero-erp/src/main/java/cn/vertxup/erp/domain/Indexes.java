/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.erp.domain;


import cn.vertxup.erp.domain.tables.ECompany;
import cn.vertxup.erp.domain.tables.EContract;
import cn.vertxup.erp.domain.tables.ECustomer;
import cn.vertxup.erp.domain.tables.EDept;
import cn.vertxup.erp.domain.tables.EEmployee;
import cn.vertxup.erp.domain.tables.EIdentity;
import cn.vertxup.erp.domain.tables.ETeam;
import cn.vertxup.erp.domain.tables.RCompanyCustomer;
import cn.vertxup.erp.domain.tables.RTeamEmployee;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>DB_ETERNAL</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index E_COMPANY_CODE = Indexes0.E_COMPANY_CODE;
    public static final Index E_COMPANY_CUSTOMER_ID = Indexes0.E_COMPANY_CUSTOMER_ID;
    public static final Index E_COMPANY_NAME = Indexes0.E_COMPANY_NAME;
    public static final Index E_COMPANY_PRIMARY = Indexes0.E_COMPANY_PRIMARY;
    public static final Index E_COMPANY_TAX_CODE = Indexes0.E_COMPANY_TAX_CODE;
    public static final Index E_CONTRACT_CODE = Indexes0.E_CONTRACT_CODE;
    public static final Index E_CONTRACT_PRIMARY = Indexes0.E_CONTRACT_PRIMARY;
    public static final Index E_CUSTOMER_PRIMARY = Indexes0.E_CUSTOMER_PRIMARY;
    public static final Index E_CUSTOMER_TAX_CODE = Indexes0.E_CUSTOMER_TAX_CODE;
    public static final Index E_DEPT_CODE = Indexes0.E_DEPT_CODE;
    public static final Index E_DEPT_NAME = Indexes0.E_DEPT_NAME;
    public static final Index E_DEPT_PRIMARY = Indexes0.E_DEPT_PRIMARY;
    public static final Index E_EMPLOYEE_PRIMARY = Indexes0.E_EMPLOYEE_PRIMARY;
    public static final Index E_EMPLOYEE_WORK_NUMBER = Indexes0.E_EMPLOYEE_WORK_NUMBER;
    public static final Index E_IDENTITY_PRIMARY = Indexes0.E_IDENTITY_PRIMARY;
    public static final Index E_IDENTITY_TYPE = Indexes0.E_IDENTITY_TYPE;
    public static final Index E_TEAM_CODE = Indexes0.E_TEAM_CODE;
    public static final Index E_TEAM_NAME = Indexes0.E_TEAM_NAME;
    public static final Index E_TEAM_PRIMARY = Indexes0.E_TEAM_PRIMARY;
    public static final Index R_COMPANY_CUSTOMER_PRIMARY = Indexes0.R_COMPANY_CUSTOMER_PRIMARY;
    public static final Index R_TEAM_EMPLOYEE_PRIMARY = Indexes0.R_TEAM_EMPLOYEE_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index E_COMPANY_CODE = Internal.createIndex("CODE", ECompany.E_COMPANY, new OrderField[] { ECompany.E_COMPANY.CODE, ECompany.E_COMPANY.SIGMA }, true);
        public static Index E_COMPANY_CUSTOMER_ID = Internal.createIndex("CUSTOMER_ID", ECompany.E_COMPANY, new OrderField[] { ECompany.E_COMPANY.CUSTOMER_ID, ECompany.E_COMPANY.SIGMA }, true);
        public static Index E_COMPANY_NAME = Internal.createIndex("NAME", ECompany.E_COMPANY, new OrderField[] { ECompany.E_COMPANY.NAME, ECompany.E_COMPANY.SIGMA }, true);
        public static Index E_COMPANY_PRIMARY = Internal.createIndex("PRIMARY", ECompany.E_COMPANY, new OrderField[] { ECompany.E_COMPANY.KEY }, true);
        public static Index E_COMPANY_TAX_CODE = Internal.createIndex("TAX_CODE", ECompany.E_COMPANY, new OrderField[] { ECompany.E_COMPANY.TAX_CODE, ECompany.E_COMPANY.SIGMA }, true);
        public static Index E_CONTRACT_CODE = Internal.createIndex("CODE", EContract.E_CONTRACT, new OrderField[] { EContract.E_CONTRACT.CODE, EContract.E_CONTRACT.SIGMA }, true);
        public static Index E_CONTRACT_PRIMARY = Internal.createIndex("PRIMARY", EContract.E_CONTRACT, new OrderField[] { EContract.E_CONTRACT.KEY }, true);
        public static Index E_CUSTOMER_PRIMARY = Internal.createIndex("PRIMARY", ECustomer.E_CUSTOMER, new OrderField[] { ECustomer.E_CUSTOMER.KEY }, true);
        public static Index E_CUSTOMER_TAX_CODE = Internal.createIndex("TAX_CODE", ECustomer.E_CUSTOMER, new OrderField[] { ECustomer.E_CUSTOMER.TAX_CODE, ECustomer.E_CUSTOMER.SIGMA }, true);
        public static Index E_DEPT_CODE = Internal.createIndex("CODE", EDept.E_DEPT, new OrderField[] { EDept.E_DEPT.CODE, EDept.E_DEPT.COMPANY_ID }, true);
        public static Index E_DEPT_NAME = Internal.createIndex("NAME", EDept.E_DEPT, new OrderField[] { EDept.E_DEPT.NAME, EDept.E_DEPT.COMPANY_ID, EDept.E_DEPT.DEPT_ID }, true);
        public static Index E_DEPT_PRIMARY = Internal.createIndex("PRIMARY", EDept.E_DEPT, new OrderField[] { EDept.E_DEPT.KEY }, true);
        public static Index E_EMPLOYEE_PRIMARY = Internal.createIndex("PRIMARY", EEmployee.E_EMPLOYEE, new OrderField[] { EEmployee.E_EMPLOYEE.KEY }, true);
        public static Index E_EMPLOYEE_WORK_NUMBER = Internal.createIndex("WORK_NUMBER", EEmployee.E_EMPLOYEE, new OrderField[] { EEmployee.E_EMPLOYEE.WORK_NUMBER, EEmployee.E_EMPLOYEE.COMPANY_ID }, true);
        public static Index E_IDENTITY_PRIMARY = Internal.createIndex("PRIMARY", EIdentity.E_IDENTITY, new OrderField[] { EIdentity.E_IDENTITY.KEY }, true);
        public static Index E_IDENTITY_TYPE = Internal.createIndex("TYPE", EIdentity.E_IDENTITY, new OrderField[] { EIdentity.E_IDENTITY.TYPE, EIdentity.E_IDENTITY.IDC_TYPE, EIdentity.E_IDENTITY.IDC_NUMBER }, true);
        public static Index E_TEAM_CODE = Internal.createIndex("CODE", ETeam.E_TEAM, new OrderField[] { ETeam.E_TEAM.CODE, ETeam.E_TEAM.DEPT_ID }, true);
        public static Index E_TEAM_NAME = Internal.createIndex("NAME", ETeam.E_TEAM, new OrderField[] { ETeam.E_TEAM.NAME, ETeam.E_TEAM.DEPT_ID, ETeam.E_TEAM.TEAM_ID }, true);
        public static Index E_TEAM_PRIMARY = Internal.createIndex("PRIMARY", ETeam.E_TEAM, new OrderField[] { ETeam.E_TEAM.KEY }, true);
        public static Index R_COMPANY_CUSTOMER_PRIMARY = Internal.createIndex("PRIMARY", RCompanyCustomer.R_COMPANY_CUSTOMER, new OrderField[] { RCompanyCustomer.R_COMPANY_CUSTOMER.COMPANY_ID, RCompanyCustomer.R_COMPANY_CUSTOMER.CUSTOMER_ID }, true);
        public static Index R_TEAM_EMPLOYEE_PRIMARY = Internal.createIndex("PRIMARY", RTeamEmployee.R_TEAM_EMPLOYEE, new OrderField[] { RTeamEmployee.R_TEAM_EMPLOYEE.TEAM_ID, RTeamEmployee.R_TEAM_EMPLOYEE.EMPLOYEE_ID }, true);
    }
}
