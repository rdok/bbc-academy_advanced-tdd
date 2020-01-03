<?php

namespace Tests\Unit\App;

use App\User;
use Tests\TestCase;

class UserTest extends TestCase
{
    /** @test */
    public function it_has_a_name()
    {
        $user = new User(['name' => 'Buzz Aldrin']);

        $this->assertSame('Buzz Aldrin', $user->name());
    }
}