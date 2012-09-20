module Screen
  module Login

    def fill_in_login_form(username,password,projectSite)
      fill_in_text("username",username)
      fill_in_text("password",password)
      fill_in_text("projectSite",projectSite)
    end

    def press_login_button
      touch_button_with_text("Login")
    end

    def empty_field_message_displayed?
      screen_has_content?("value is required")
    end

    def invalid_credentials_error_message_displayed?
      screen_has_content?("You're not authorized to login'")
    end

  end
end


World(Screen::Login)