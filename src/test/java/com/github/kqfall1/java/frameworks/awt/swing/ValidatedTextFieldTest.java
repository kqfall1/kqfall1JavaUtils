package com.github.kqfall1.java.frameworks.awt.swing;

import com.github.kqfall1.java.enums.YesNoInput;
import com.github.kqfall1.java.interfaces.inputters.InputterTests;
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
        final var optionalNumber = InputterTests.numberInputterTest(validatedJTextField, prompt, lowerBound, upperBound).join();
        optionalNumber.ifPresent(number -> Assertions.assertEquals(Double.valueOf(validatedJTextField.getText()), optionalNumber.get()));
        return optionalNumber;
    }

    @RepeatedTest(200)
    public void getNumberTest()
    {
        var lowerBound = InputterTests.getPositiveRandomNumber(Optional.empty());
        prompt = InputterTests.getNewPrompt(prompt);
        var upperBound = InputterTests.getPositiveRandomNumber(Optional.of(10.0));
        validatedJTextField.setText("  2.054723  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = InputterTests.getPositiveRandomNumber(Optional.empty()) - 25;
        prompt = InputterTests.getNewPrompt(prompt);
        upperBound = InputterTests.getPositiveRandomNumber(Optional.empty());
        validatedJTextField.setText("  -20.4093242  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = 0.0;
        upperBound = 10.0;
        validatedJTextField.setText("  0.0000000  ");
        _getNumberTest(lowerBound, upperBound);
        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("  10.0000000000  ");
        _getNumberTest(lowerBound, upperBound);

        lowerBound = InputterTests.getPositiveRandomNumber(Optional.empty());
        upperBound = InputterTests.getPositiveRandomNumber(Optional.of(10.0));
        validatedJTextField.setText("  200.2940213  ");
        var optionalDouble = _getNumberTest(lowerBound, upperBound);
        Assertions.assertTrue(optionalDouble.isEmpty());

        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("  -200.7432845806  ");
        optionalDouble = _getNumberTest(lowerBound, upperBound);
        Assertions.assertTrue(optionalDouble.isEmpty());
    }

    private Optional<String> _getStringTest(Optional<String[]> validStrings)
    {
        final var optionalString = InputterTests.stringInputterTest(validatedJTextField, prompt, validStrings).join();
        optionalString.ifPresent(string -> Assertions.assertEquals(validatedJTextField.getText(), optionalString.get()));
        return optionalString;
    }

    @Test
    public void getStringTest()
    {
        prompt = InputterTests.getNewPrompt(prompt);
        var validStrings = Optional.<String[]>empty();
        validatedJTextField.setText("  ");
        _getStringTest(validStrings);

        prompt = InputterTests.getNewPrompt(prompt);
        validStrings = Optional.of(new String[] { "  blue  ", "~12GReeN", "_-@yellow" });
        validatedJTextField.setText("  blue  ");
        _getStringTest(validStrings);
        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("~12GReeN");
        _getStringTest(validStrings);
        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("_-@yellow");
        _getStringTest(validStrings);

        prompt = InputterTests.getNewPrompt(prompt);
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
        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("  YeS  ");
        var optionalYesNo = InputterTests.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isPresent());
        Assertions.assertEquals(YesNoInput.YES, optionalYesNo.get());

        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("  nO  ");
        optionalYesNo = InputterTests.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isPresent());
        Assertions.assertEquals(YesNoInput.NO, optionalYesNo.get());

        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("SomethingElse");
        optionalYesNo = InputterTests.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isEmpty());
        prompt = InputterTests.getNewPrompt(prompt);
        validatedJTextField.setText("Example");
        optionalYesNo = InputterTests.yesNoInputterTest(validatedJTextField, prompt).join();
        Assertions.assertTrue(optionalYesNo.isEmpty());
    }
}