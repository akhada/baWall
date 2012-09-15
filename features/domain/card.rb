module Domain
  module Card

   def search_card_in_standard_text_feature(story_number)
     fill_in_card_number(story_number)
     press_login_button
   end

  end
end



World(Domain::Card)