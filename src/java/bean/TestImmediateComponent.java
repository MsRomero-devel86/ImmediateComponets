package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author meghan
 */
@Named(value = "testImmediateComponent")
@SessionScoped
public class TestImmediateComponent implements Serializable, ValueChangeListener {

    private String name;
    private String lanuage = "en";//deflaut
    private Map<String, String> lanuageMap;

    public TestImmediateComponent() {
        lanuageMap = new LinkedHashMap<String, String>();
        lanuageMap.put("US", "en");
        lanuageMap.put("DE", "de");
        lanuageMap.put("GR", "el");
        lanuageMap.put("RU", "ru");
        lanuageMap.put("CN", "zn");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanuage() {
        return lanuage;
    }

    public void setLanuage(String lanuageCode) {
        this.lanuage = lanuageCode;
    }

    public Map<String, String> getLanuageMap() {
        return lanuageMap;
    }

    public void setLanuageMap(Map<String, String> lanuageMap) {
        this.lanuageMap = lanuageMap;
    }

    public String newData() {
        lanuage.toString();
        return name;
    }

    public void lanuageChange(ValueChangeEvent e) {
        FacesContext contextInstance = FacesContext.getCurrentInstance();
        for (Map.Entry<String, String> entry : this.lanuageMap.entrySet()) {
            Object o = e.getNewValue();
            String lang = entry.getValue();
            if (lang.equals(o.toString())) {
                contextInstance.getViewRoot().setLocale(new Locale(o.toString(),
                        entry.getKey()));
                this.lanuage = o.toString();
                name = null;
                break;
            }
        }
        contextInstance.renderResponse();

    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        TestImmediateComponent newBean = (TestImmediateComponent) FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().get(event);
        newBean.setName(event.getNewValue().toString());
    }
}
/*    
this lanuage change method works the same as the one above!
String langAbreviation = this.lanuage;
        String langIntials = null;
        for (Map.Entry<String, String> entry : lanuageMap.entrySet()) {
            if (Objects.equals(langAbreviation, entry.getValue())) {
                langIntials = entry.getKey();
                break;
            }
        }
        if (langIntials != null) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(
                    new Locale(langAbreviation, langIntials));
        
        
        lanuage = e.getNewValue().toString();
        FacesContext.getCurrentInstance().renderResponse();
    }*/
