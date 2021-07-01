
	WebElement table;
	WebDriver driver;
	public List<Customers> getCustomerRows(){
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
		List<Customers> customerRows = new ArrayList<Customers>();
		for(WebElement row : rows){
			customerRows.add(new Customers(row));
		}
		return customerRows;
	}
	
	class Customers{
		public WebElement id;
		public String idText,name;
		public Customers(WebElement element){
			id = element.findElement(By.id("id"));
			name = element.findElement(By.id("name")).getText();
			idText=id.getText();
		}
	}
	

	public void get(){
		List<Customers> c = getCustomerRows();
		Customers row=null;
		for(Customers c1: c){
			if(c1.idText.equals("Created")){
				row=c1;
			}
		}
		Assert.assertNotNull(row,"Customer not created");
		Assert.assertEquals(row.name, "CreatedName","Name does not match");
	}
	

