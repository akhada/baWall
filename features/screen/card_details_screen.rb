module Screen
  module CardDetails

    def press_owner_dropdown
      press_button_with_id("issueAssignee")
    end

    def choose_owner(owner)
      click_on_text(owner)
    end

    def press_state_spinner
      press_button_with_id("issueTransitions")
    end

    def select_state(state)
      click_on_text(state)
    end



  end
end


World(Screen::CardDetails)