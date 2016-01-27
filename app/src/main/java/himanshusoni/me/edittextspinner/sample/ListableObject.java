package himanshusoni.me.edittextspinner.sample;

import me.himanshusoni.edittextspinner.EditTextSpinner;

/**
 * Created by himanshusoni on 27/01/16.
 */
public class ListableObject implements EditTextSpinner.Listable {

    private String name, email;

    public ListableObject(String name, String email) {
        this.email = email;
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return name;
    }
}
