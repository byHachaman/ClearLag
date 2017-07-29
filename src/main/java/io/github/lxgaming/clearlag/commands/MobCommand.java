/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.clearlag.commands;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import io.github.lxgaming.clearlag.commands.mob.MobAddCommand;
import io.github.lxgaming.clearlag.commands.mob.MobListCommand;
import io.github.lxgaming.clearlag.commands.mob.MobRemoveCommand;
import io.github.lxgaming.clearlag.commands.mob.MobValidateCommand;
import io.github.lxgaming.clearlag.util.Reference;
import io.github.lxgaming.clearlag.util.SpongeHelper;

public class MobCommand extends Command {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Text.Builder textBuilder = Text.builder();
		textBuilder.append(Text.of(TextColors.DARK_GREEN, "Mob sub commands:"));
		for (Command command : getSubCommands()) {
			if (StringUtils.isNotBlank(command.getPermission()) && !src.hasPermission(command.getPermission())) {
				continue;
			}
			
			textBuilder.append(Text.of(Text.NEW_LINE, TextColors.GREEN, "/" + Reference.PLUGIN_NAME, TextColors.WHITE, " ", getName(), " ", command.getUsage()));
		}
		
		src.sendMessage(Text.of(SpongeHelper.getTextPrefix(), textBuilder.build()));
		return CommandResult.success();
	}
	
	@Override
	public String getName() {
		return "Mob";
	}
	
	@Override
	public List<Command> getSubCommands() {
		return Arrays.asList(
				new MobAddCommand(),
				new MobListCommand(),
				new MobRemoveCommand(),
				new MobValidateCommand());
	}
}