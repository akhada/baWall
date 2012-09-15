module Screen
  module Login

    def fill_in_card_number(story_number)
      fill_in_text("issueNumber",story_number)
    end

    def press_search_button
      touch_button("loginButton")
    end

  end
end


World(Screen::Login)