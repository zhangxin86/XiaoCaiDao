package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by liyuxuan on 2016/11/27.
 */

public class StepItem {
    private String stepTitle;
    private String stepImg;

    public StepItem(){}

    public StepItem(String stepTitle, String stepImg) {
        this.stepTitle = stepTitle;
        this.stepImg = stepImg;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getStepImg() {
        return stepImg;
    }

    public void setStepImg(String stepImg) {
        this.stepImg = stepImg;
    }
}
