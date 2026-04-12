package com.github.kqfall1.java.frameworks.awt.swing;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.InputterTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Optional;

public final class ValidatedTextFieldTest
{
    Optional<String> prompt = Optional.empty();
    ValidatedJTextField validatedJTextField;

    @BeforeEach
    public void init()
    {
        validatedJTextField = new ValidatedJTextField();
    }

    private Optional<Double> _getNumberTest(double lowerBound, double upperBound)
    {
        final var optionalNumber = InputterTestUtils.numberInputterTest(validatedJTextField, prompt, lowerBound, upperBound).join();
        optionalNumber.ifPresent(number -> Assertions.assertEquals(Double.valueOf(validatedJTextField.getText()), optionalNumber.get()));
        return optionalNumber;
    }

    @RepeatedTest(200)
    public void getNumberTest()
    {
        var lowerBound = InputterTestUtils.getPositiveRandomNumber(Optional.empty());
        prompt = InputterTestUtils.getNewPrompt(prompt);
        var upperBound = InputterTestUtils.getPositiveRandomNumber(Optional.of(10.0));
        validatedJTextField.setText("  2.054723  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = InputterTestUtils.getPositiveRandomNumber(Optional.empty()) - 25;
        prompt = InputterTestUtils.getNewPrompt(prompt);
        upperBound = InputterTestUtils.getPositiveRandomNumber(Optional.empty());
        validatedJTextField.setText("  -20.4093242  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = 0.0;
        upperBound = 10.0;
        validatedJTextField.setText("  0.0000000  ");
        _getNumberTest(lowerBound, upperBound);
        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("  10.0000000000  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = InputterTestUtils.getPositiveRandomNumber(Optional.empty());
        upperBound = InputterTestUtils.getPositiveRandomNumber(Optional.of(10.0));
        validatedJTextField.setText("  200.2940213  ");
        var optionalDouble = _getNumberTest(lowerBound, upperBound);
        Assertions.assertTrue(optionalDouble.isEmpty());

        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("  -200.7432845806  ");
        optionalDouble = _getNumberTest(lowerBound, upperBound);
        Assertions.assertTrue(optionalDouble.isEmpty());
    }

    private Optional<String> _getStringTest(Optional<String[]> validStrings)
    {
        final var optionalString = InputterTestUtils.stringInputterTest(validatedJTextField, prompt, validStrings).join();
        optionalString.ifPresent(string -> Assertions.assertEquals(validatedJTextField.getText(), optionalString.get()));
        return optionalString;
    }

    @Test
    public void getStringTest()
    {
        prompt = InputterTestUtils.getNewPrompt(prompt);
        var validStrings = Optional.<String[]>empty();
        validatedJTextField.setText("  ");
        _getStringTest(validStrings);

        prompt = InputterTestUtils.getNewPrompt(prompt);
        validStrings = Optional.of(new String[] { "  blue  ", "~12GReeN", "_-@yellow" });
        validatedJTextField.setText("  blue  ");
        _getStringTest(validStrings);
        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("~12GReeN");
        _getStringTest(validStrings);
        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("_-@yellow");
        _getStringTest(validStrings);

        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("SomethingElse");
        var optionalString = _getStringTest(validStrings);
        Assertions.assertTrue(optionalString.isEmpty());
        validatedJTextField.setText("Example");
        optionalString = _getStringTest(validStrings);
        Assertions.assertTrue(optionalString.isEmpty());
    }

    @Test
    public void getYesNoTest()
    {
        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("  YeS  ");
        var optionalYesNo = InputterTestUtils.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isPresent());
        Assertions.assertEquals(YesNoInput.YES, optionalYesNo.get());

        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("  nO  ");
        optionalYesNo = InputterTestUtils.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isPresent());
        Assertions.assertEquals(YesNoInput.NO, optionalYesNo.get());

        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("SomethingElse");
        optionalYesNo = InputterTestUtils.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isEmpty());
        prompt = InputterTestUtils.getNewPrompt(prompt);
        validatedJTextField.setText("Example");
        optionalYesNo = InputterTestUtils.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isEmpty());
    }
}