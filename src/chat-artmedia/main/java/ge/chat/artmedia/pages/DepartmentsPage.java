package ge.chat.artmedia.pages;

import ge.chat.artmedia.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepartmentsPage extends BasePage {
    public DepartmentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "settings")
    WebElement settingsNavButton;

    @FindBy(id = "Departments")
    WebElement departmentsNavButton;

    @FindBy(id = "add_department_action")
    WebElement addDepartmentsButton;

    @FindBy(id = "name")
    WebElement departmentsNameField;

    @FindBy(id = "users")
    WebElement agentsSelector;

    public void addDepartment(String text){
        clickToElement(settingsNavButton);
        clickToElement(departmentsNavButton);
        clickToElement(addDepartmentsButton);
        sendText(departmentsNameField, text);
        clickToElement(agentsSelector);
    }

    public void deleteDepartment(){
    
    }
}
