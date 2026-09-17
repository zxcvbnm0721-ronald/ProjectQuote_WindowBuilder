package com;

public class testMain{

	public static void main(String[] args) {

		// ==================================================
    	// field：封裝
    	// ==================================================

    	int[][] workHours={{12,18},{20,25,16,14},{10,12,8}};

    	ProjectQuote p1=new ProjectQuote(
    		1001,180000,850,5,2,1,true,true,workHours);

    	p1.calculate();

    	System.out.println(p1.show());
    	System.out.println();
		System.out.println(ProjectQuote.showCompanySummary());

		// 避免 Console 測試影響 GUI static 統計
		ProjectQuote.resetCompanyDate();

		// ==================================================
        // 啟動 GUI
        // ==================================================

        /*JFrameUI frame=new JFrameUI();
        frame.setVisible(true);*/	


	}

	


}
