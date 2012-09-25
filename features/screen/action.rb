module Screen
  module Action

    def fill_in_text(field,value)
      performAction('enter_text_into_id_field', value, field)
    end

    def press_text_field(field)
      performAction('click_on_view_by_id', field)
    end

    def touch_button_with_text(text)
      performAction('press_button_with_text',text)
    end

    def screen_has_content?(text)
      performAction('assert_text',text, true)
    end

    def press_keypad_enter_button
      performAction('send_key_enter')
    end

    def press_button_with_id(button_id)
      performAction('click_on_view_by_id',button_id)
    end

    def click_on_text(item)
      performAction('click_on_text',item)
    end

    def wait_for_no_progress_bar
      performAction('wait_for_no_progress_bars')
    end


    def loop_until
      30.times do
        break if yield
        sleep 1
      end
    end

  end
end



World(Screen::Action)
