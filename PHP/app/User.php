<?php

namespace App;

class User
{
    /** @var array  */
    private $attributes;

    public function __construct(array $attributes)
    {
        $this->attributes = $attributes;
    }

    public function name()
    {
        return $this->attributes['name'];
    }
}