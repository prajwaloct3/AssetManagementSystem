package com.cg.ams.presentation;

import java.util.Scanner;

import com.cg.ams.bean.AssetAllocationBean;
import com.cg.ams.bean.EmployeeBean;
import com.cg.ams.exception.AMSException;
import com.cg.ams.service.AssetServiceImpl;
import com.cg.ams.service.IAssetService;

public class ManagerConsole {
	IAssetService service = new AssetServiceImpl();
	AssetAllocationBean allocationbean = new AssetAllocationBean();
	EmployeeBean employeeBean = new EmployeeBean();
	Scanner scanner = new Scanner(System.in);
	int adminFlag = 0;

	public void start(String userName) throws AMSException {
		while (true) {
			System.out.println("1.Allocate The request");
			System.out.println("2.Deallocate the Request");
			System.out.println("3.Exit");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				int allocationId = 0;
				System.out.println("Enter the Employee id.");
				int empId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("enter asset name");
				String assetname = scanner.nextLine();
				int quantity = service.validAsset(assetname);
				if(quantity>0) {
				employeeBean.setEmpNo(empId);
				allocationbean.setAssetName(assetname);
				 allocationId = service.allocateAsset(empId, assetname);
				allocationbean.setAllocationId(allocationId);
				}
				else {
					System.err.println("Wrong asset name entered");
				}
				

				if (allocationId > 0) {

					System.out.println("Allocation request generated for employee number: " + empId
							+ " with request id: " + allocationbean.getAllocationId());
					adminFlag = 1;
				} else {
					System.out.println("Request can not be generated at the moment");
				}

				break;
			case 2:
				int allocationId1 = 0;
				System.out.println("Enter the Employee id.");
				int empId1 = scanner.nextInt();
				scanner.nextLine();
				System.out.println("enter asset name");
				String assetname1 = scanner.nextLine();
				int quantity1 = service.validAsset(assetname1);
				if(quantity1>0) {
				employeeBean.setEmpNo(empId1);
				allocationbean.setAssetName(assetname1);
				allocationId1 = service.deallocateAsset(empId1, assetname1);
				allocationbean.setAllocationId(allocationId1);
				}
				else {
					System.err.println("Wrong asset name entered");
				}
				if (allocationId1 > 0) {

					System.out.println("Deallocation request generated for employee number: " + empId1
							+ " with request id: " + allocationbean.getAllocationId());
					adminFlag = -1;
				} else {
					System.out.println("Request can not be generated at the moment");
				}
				break;

			case 3:
				System.out.println("Exited from manager");
				System.exit(0);
				break;
			}
		}
	}
}
