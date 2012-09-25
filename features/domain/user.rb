module Domain
  module User

    def login(username,password, project_site)
      fill_in_login_form(username,password, project_site)
      press_login_button
      wait_for_no_progress_bar
    end

    def validate_mandatory_field_error_message
      empty_field_message_displayed?
    end

    def validate_invalid_credentials_error_message
      invalid_credentials_error_message_displayed?
    end

    def logout
      press_menu_button
      press_log_out
    end

  end
end



World(Domain::User)