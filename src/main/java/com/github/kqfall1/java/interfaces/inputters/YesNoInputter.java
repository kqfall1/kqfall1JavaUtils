package com.github.kqfall1.java.interfaces.inputters;

import com.github.kqfall1.java.enums.YesNoInput;

/**
 * Defines a contract for {@code YesNoInput} from actors.
 * @author Quinn Keenan
 * @since 23/10/2025
 */
@FunctionalInterface
public interface YesNoInputter
{
	YesNoInput getYesNo(String prompt);
}