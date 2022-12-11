import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PlayerTest {

    @Test
    public void playerNameSetting() {
        Player player = new Player();
        assertEquals("Player", player.getName());
    }
}
