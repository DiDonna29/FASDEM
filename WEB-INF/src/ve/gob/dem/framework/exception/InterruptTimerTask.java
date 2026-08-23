package ve.gob.dem.framework.exception;

import java.util.TimerTask;
/**
 * @author marcenrl
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InterruptTimerTask extends TimerTask{
    private Thread theTread;

    public InterruptTimerTask(Thread theTread) {
        this.theTread = theTread;
    }

    @Override
    public void run() {
        theTread.interrupt();
    }

}
