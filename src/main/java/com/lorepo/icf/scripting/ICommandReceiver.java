package com.lorepo.icf.scripting;

import java.util.List;

public interface ICommandReceiver {

	public String getName();
	public String executeCommand(String commandName, List<IType> params);
}
