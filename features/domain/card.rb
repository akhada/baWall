module Domain
  module Card

   def search_card_in_standard_text_feature(story_number)
     fill_in_card_number(story_number)
     press_button_with_id("fakeButton")
   end

    def assign_card_to(owner)
      press_owner_dropdown
      choose_owner(owner)
      wait_for_no_progress_bar
    end

   def change_card_state_to(state)
     press_state_spinner
     select_state(state)
     wait_for_no_progress_bar
   end


  end
end



World(Domain::Card)
