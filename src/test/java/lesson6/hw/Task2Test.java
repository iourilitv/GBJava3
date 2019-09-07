package lesson6.hw;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Task2Test {

    @Test(expected = RuntimeException.class)
    public void checkArrayWithSamplesNullInitArrayTest(){
        int[] initArray = null;
        int[] samples = {1, 4};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test(expected = RuntimeException.class)
    public void checkArrayWithSamplesNullSamplesArrayTest(){
        int[] initArray = {1, 4};
        int[] samples = null;
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test(expected = RuntimeException.class)
    public void checkArrayWithSamplesEmptyInitArrayTest(){
        int[] initArray = {};
        int[] samples = {1, 4};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test(expected = RuntimeException.class)
    public void checkArrayWithSamplesEmptySamplesArrayTest(){
        int[] initArray = {1, 4};
        int[] samples = {};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test
    public void checkArrayWithSamplesTrueArrayTest(){
        int[] initArray = {1, 1, 1, 4, 4, 1, 4, 4};
        int[] samples = {1, 4};
        Assert.assertTrue(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test
    public void checkArrayWithSamplesFalseNotAllSamplesExistInArrayTest1(){
        int[] initArray = {1, 1, 1, 1, 1, 1};
        int[] samples = {1, 4};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test
    public void checkArrayWithSamplesFalseNotAllSamplesExistInArrayTest2(){
        int[] initArray = {4, 4, 4, 4};
        int[] samples = {1, 4};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

    @Test
    public void checkArrayWithSamplesFalseAlienInArrayTest(){
        int[] initArray = {1, 4, 4, 1, 1, 4, 3};
        int[] samples = {1, 4};
        Assert.assertFalse(Task2.checkArrayWithSamples(initArray, samples));
    }

}