package Compilation.test.jUnitTest;

import Compilation.yal.exceptions.AnalyseSemantiqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class ArbreAbstraitTest {
    @Test (expected = AnalyseSemantiqueException.class)
    public void test(){
        int i = 5;
        throw new AnalyseSemantiqueException(1,"a");
    }



}
