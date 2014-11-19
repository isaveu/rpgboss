package rpgboss.editor.dialog.cmd

import scala.swing._
import rpgboss.model.event.EventCmd
import rpgboss.editor.dialog._
import rpgboss.model.event._
import rpgboss.editor.StateMaster
import rpgboss.lib.Utils
import rpgboss.model.RpgEnum

object EventCmdDialog {
  /**
   * This function gets a dialog for the given EventCmd
   *
   * One may argue here that it's not object oriented to case match through
   * all the possible types searching for the right dialog, and that we should
   * use polymorphism.
   *
   * I generally agree, but feel that adding UI details to the model is
   * more disgusting than this hack.
   */
  def dialogFor(
    owner: Window,
    sm: StateMaster,
    mapName: Option[String],
    evtCmd: EventCmd,
    successF: (EventCmd) => Any) = {
    evtCmd match {
      case e: AddRemoveItem =>
        new AddRemoveItemCmdDialog(owner, sm, e, successF)
      case e: ModifyParty => new ModifyPartyCmdDialog(owner, sm, e, successF)
      case e: ShowText => new ShowTextCmdDialog(owner, e, successF)
      case e: Teleport => new TeleportCmdDialog(owner, sm, e, successF)
      case e: SetEventState =>
        new SetEventStateDialog(owner, sm, mapName, e, successF)
      case e: MoveEvent =>
        new MoveEventCmdDialog(owner, sm, mapName, e, successF)
      case e: StartBattle =>
        new StartBattleCmdDialog(owner, sm, e, successF)
      case e: RunJs => new RunJsCmdDialog(owner, e, successF)
      case _ => null
    }
  }
}