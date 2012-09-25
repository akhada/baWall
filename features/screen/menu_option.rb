module Screen
  module MenuOption

    def press_menu_button
      performAction('press_menu')
    end

    def press_log_out
      performAction('select_from_menu', "Log out")
    end

    def press_list_item(project)
      click_on_text(project)
    end

  end
end


World(Screen::MenuOption)