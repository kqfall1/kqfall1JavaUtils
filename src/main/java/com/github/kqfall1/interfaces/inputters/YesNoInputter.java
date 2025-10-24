package com.github.kqfall1.interfaces.inputters;

import com.github.kqfall1.enums.YesNoInput;

/**
 * Defines a contract for {@code YesNoInput} from actors.
 * @author Quinn Keenan
 * @since 23/10/2025
 */
@FunctionalInterface
public interface YesNoInputter
{
	/**
	 * @param prompt A string displayed to inform the actor of requested information.
	 *               A colon is appended to {@code prompt}.
 	*/
	YesNoInput getYesNo(String prompt);
}