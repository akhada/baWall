module Screen
  module Action

    def fill_in_text(field,value)
      performAction('enter_text_into_named_field', value, field)
    end

    def touch_button(identifier)
      performAction('press',identifier)
    end

    def screen_has_content?
      performAction('assert_text',text, true)
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
