package com;

public class ProjectQuote
{
	// ==================================================
    // field：封裝
    // ==================================================
    private int projectNo;//專案編號（數值化）
    private int budgetLimit;//客戶預算上限
    private int hourlyCost;//公司每小時成本
    private double discountRate;//折扣率百分比
    private int projectType;//1=Web、2=AI、3=Data
    private int clientLevel;//1=一般、2=VIP
    private boolean urgent;//是否急件
    private boolean maintenance;//是否含維護服務
    private int[][] workHours;//Jagged Array：各工作群組的階段工時
    private int totalHours;//專案總工時
    private double laborCost;//人工成本
    private double quotedPrice;//折扣前報價
    private double finalPrice;//最終報價

    // ==================================================
    // static：公司統計
    // ==================================================
    private static int projectCount=0;
    private static double companyFinalPriceTotal=0;

    // ==================================================
    // constructor
    // ==================================================

    public ProjectQuote(
    		int projectNo,
    		int budgetLimit,
    		int hourlyCost,
    		double discountRate,
    		int projectType,
    		int clientLevel,
    		boolean urgent,
    		boolean maintenance,
    		int[][] workHours)
    	{
    		setProjectNo(projectNo);
    		setBudgetLimit(budgetLimit);
    		setHourlyCost(hourlyCost);
    		setDiscountRate(discountRate);
    		setProjectType(projectType);
    		setClientLevel(clientLevel);
    		setUrgent(urgent);
    		setMaintenance(maintenance);
    		setWorkHours(workHours);

    		projectCount++;		
    	}

    // ==================================================
    // setter / getter
    // ==================================================

    public void setProjectNo(int projectNo){
    	if(projectNo>0){
    		this.projectNo=projectNo;
    	}
    }

    public int getProjectNo(){
    	return projectNo;
    }

    public void setBudgetLimit(int budgetLimit) {
        if (budgetLimit > 0) {
            this.budgetLimit = budgetLimit;
        }
    }

    public int getBudgetLimit() {
        return budgetLimit;
    }

    public void setHourlyCost(int hourlyCost) {
        if (hourlyCost > 0) {
            this.hourlyCost = hourlyCost;
        }
    }

    public int getHourlyCost() {
        return hourlyCost;
    }

    public void setDiscountRate(double setDiscountRate){
    	if(discountRate>=0&&discountRate<=30){
    		this.discountRate=discountRate;
    	}
    }

	public double getDiscountRate(){
		return discountRate;
	}

	public void setProjectType(int projectType){
		if(projectType>=1&&projectType<=3){
			this.projectType=projectType;
		}
	}

	public int getprojectType(){
		return projectType;
	}

	public void setClientLevel(int clientLevel){
		if(clientLevel==1||clientLevel==2){
			this.clientLevel=clientLevel;
		}
	}

	public int getClientLevel(){
		return clientLevel;
	}

	public void setUrgent(boolean urgent){
		this.urgent=urgent;
	}

	public boolean isUrgent(){
		return urgent;
	}

	public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setWorkHours(int[][] workHours){
    	this.workHours=workHours;
    	// setter 負責資料規則:負數工時改為0
    	for(int i=0;i<this.workHours.length;i++){
    		for(int j=0;j<this.workHours[i].length;j++){
    			if(this.workHours[i][j]<0){
    			this.workHours[i][j]=0;

    			}
    		
    		}
    	}
    }

    public int[][] getWorkHours() {
        return workHours;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public double getQuotedPrice() {
        return quotedPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    // ==================================================
    // methods
    // ==================================================

    //計算總時數
    public int calculateTotalHours(){

    	totalHours=0;
    	for(int i=0;i<workHours.length;i++){
    	
    		//Jagged Array:內層一定使用 workHours[i].length
    		for(int j=0;j<workHours[i].length;j++){
    			totalHours+=workHours[i][j];
    		}
    	}

    	return totalHours;

    }

    //計算人工成本」或「計算勞力成本」
    public double calculateLaborCost(){
    	
    	laborCost=totalHours*hourlyCost;

    	return laborCost;
    }


    //「專案加價率」或「專案利潤率」
    public double getProjectMarkupRate(){

    	if(projectType==1){
    		return 0.15;
    	}else if(projectType==2){
    		return 0.25;
    	}else{
    		return 0.20;
    	}
    }

    //常都是用來「索取、讀取」類別裡面的某個資料。
    public String getProjectTypeName(){
        if(projectType==1){
            return "Web";
        }else if(projectType==2){
            return "AI";
        }else{
            return "Data";
        }
    }

    //取得 (get) 客戶 (Client) 等級 (Level) 名稱 (Name)」。
    public String getClientLevelName() {

        if (clientLevel == 2) {
            return "VIP";
        }

        return "一般";
    }

    //計算
    public void calculate(){

        calculateTotalHours();
        calculateLaborCost();

        // 1. 人工成本 + 專案類型加成
        double typeMarkup=laborCost*getProjectMarkupRate();

        double price=laborCost+typeMarkup;

        // 2. 急件：前述報價再加 12%
        if(urgent){
            price=price+price*0.12;

        }

        // 3. 維護服務：固定加 18,000
        if(maintenance){
            price=price+18000;
        }

        quotedPrice=price;

        // 4. 一般折扣
        finalPrice=quotedPrice*(1-discountRate/100.0);

        // 5. VIP 再折 3%
        if(clientLevel==2){
            finalPrice=finalPrice*0.97;
        }

        companyFinalPriceTotal+=finalPrice;

    }

    //『取得狀態（getStatus）』的功能，你不用給我任何資料，呼叫它之後，它就會還給你一串純文字（String）
    public String getStatus(){
        if(finalPrice<=budgetLimit){
            return "可承接";
        }else if(finalPrice-budgetLimit<=budgetLimit*0.10){
            return "需協商";
        }else{
            return"超出預算";
        }
    }

    public String show(){
        String result="";

        result+="=====企業客戶專案報價=====";
        result+="\n專案編號:"+projectNo;
        result+="\n專案類型:"+getProjectTypeName();
        result+="\n客戶等級:"+getClientLevelName();
        result+="\n預算上限:"+budgetLimit;
        result+="\n每小時成本:"+hourlyCost;
        result+="\n折扣率:"+discountRate+"%";
        result+="\n急件:"+(urgent?"是":"否");
        result+="\n維護服務:"+(maintenance? "是":"否");

        result+="\n\n=====Jagged Array 工時=====";
        
        for(int i=0;i<workHours.length;i++){
            result+="\n工作群組"+(i+1)+":";

            for(int j=0;j<workHours[i].length;j++){
                result+=workHours[i][j];

                if(j<workHours[i].length-1){
                    result+=",";
                }

            }


        }
        result+="\n\n總工時:"+totalHours;
        result+="\n人工成本:"+laborCost;
        result+="\n折扣前報價:"+quotedPrice;
        result+="\n最終報價:"+finalPrice;
        result+="\n案件狀態:"+getStatus();

        return result;

    }
    // ==================================================
    // static methods
    // ==================================================

    //「取得 (get) 專案 (Project) 數量 (Count)」
    public static int getProjectCount(){
        return projectCount;
    }

    //「取得 (get) 公司 (Company) 最終 (Final) 價格 (Price) 總計 (Total)」
    public static double getCompanyFinalPriceTotal(){
        return companyFinalPriceTotal;
    }

    //「顯示 (show) 公司 (Company) 總結 (Summary)」
    public static String showCompanySummary(){
        return"===== static 公司報價統計====="
                +"\n以計算專案數:"+projectCount
                +"\n公司累計最終報價:"+companyFinalPriceTotal;
    }

    //重置 (reset) 公司 (Company) 資料 (Data)」
    public static void resetCompanyDate(){
        projectCount=0;
        companyFinalPriceTotal=0;
    }
}