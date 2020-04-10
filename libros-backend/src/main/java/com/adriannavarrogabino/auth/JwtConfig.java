package com.adriannavarrogabino.auth;

public class JwtConfig {

	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEA5cXV0TzEOVNO+SRCFrZXrIo947RredHQKmiykABoIdGT9Wp/\r\n" + 
			"fcloYwSxpV+C6UDL/UqNzdgkNpUNsNoIIpmqnaMmoGULfRUm/cv2ElbLQNN+O1rw\r\n" + 
			"ykxFffiQ4hqj4+vnYheNfyiGnuppJKsIMEo2Gv7PeIPbbs5iiNeektZbNjfEeQ7V\r\n" + 
			"S+eDGW6PkTxvSraPzK+p0G/dsOPecMjkQpgn1dzu6s4WJPhHJyz6BF3ty+W+uGFi\r\n" + 
			"bxWD1DIjRtNZaUTh9oKf3sZIDkxxBBMcXFu5Alj3Jcd/LotwHMQpKcEtG2FFBZuM\r\n" + 
			"zyJ2dt2X7wp5ZrJJU2BhvH0UD7AzFP9LFF95BQIDAQABAoIBAQDiwCiw81nmBJvD\r\n" + 
			"7I834LyG7moDI5KIxpC6uVW2HGCnNy7fmzizxqY4SXKSc746OBLXJNLqF+os302C\r\n" + 
			"gLhUhPdF17MPyIfc6ZnwDm8JTBQLJmvFYYqM6VYO6dT4/TdNfwl52SpvGWw4ubYH\r\n" + 
			"VK2hEitw2pOeJ+nN4OiGiNrZp6adye5EIDrIey1Y1x+21XcpOfYMWBNVQFuH5GuZ\r\n" + 
			"z5O4o5BbghoK8eVOV+QU8TMzi/ABAhXhiqk8RRaav6kzXXckuWK9yZiGysA8FabX\r\n" + 
			"FrL7M4WhLKNqtUEXvVgBzaLbNPYnsZMVVaLbKVJ97WnTvRLQZJyu3DhRu6WuPbSG\r\n" + 
			"91Wuy2slAoGBAPj6cVBsTrR+HBuY6dhkdLFZ446ero+LYZtT8NDHyekLGFlObhy/\r\n" + 
			"6RQ4XE8hwaoBdaVSXIvZHQCT8r0a437JIA8kmiuccGY+XOh5H9y92sHsoBa09mGI\r\n" + 
			"M9YgHxqOg6elaN92pDMcTcAFsuuvabkWX2JV/hx2670AJybRzG5BE9ZbAoGBAOxA\r\n" + 
			"u+YkBfv44aYFtGrtrbTcn+jfLeOPyL6sLfnaZnjGtnylXUUMQ1rse4pvur/EdegK\r\n" + 
			"YxzhdhW1/ygoVxdVIP98929UXkDxDAJkBMV709X4p9mVaIMJVx+h/IEOUsKHtVMz\r\n" + 
			"UGDvXyZkb/SCgswEN4HGf8ZSpl9ipS8fxt30+MwfAoGAaui3MW5gWVZxYVRGzVlN\r\n" + 
			"Vj4f3US79f1GmzjvBedUBtJx0R9BXuz0+268DGqVSxe3WZbFEHPY5T+iEptyJiXc\r\n" + 
			"s0PnQ2S4St7qbBRenx2SyoR9tWJdDUI75+0BDhk15Q28u3+pQB0eMZ8A1f0La6N0\r\n" + 
			"r7f3FmfmD9D5/sxFeO5Ow1MCgYEAs8LFteUvEHxc5YBtuPbEN1uv6Pb36bCb5EkK\r\n" + 
			"apQ9aCR68fi5MTKVhXduwPUmuYd412gf5fe/a+GhFiMrsTs68mtZskIVvYArzR3h\r\n" + 
			"a4o+sw6SWMKQVTQkMJOGl4QYgJ/V3kqrHDbGxWWisf8kGqwSuBIbYwG8SdO7MFIT\r\n" + 
			"dQtSy60CgYEAgzSWxOK05FMtNrN7T4RTlOP6Q5iCMFMXLbJSWjHwMpwMhGX38m9B\r\n" + 
			"lWE7tfWcOXvHh6bNvzkziGIzgjzaWN9Wh2FUqj+LOFQdsh+zZ6g1ivfLGWcdpKsd\r\n" + 
			"rwJeM1+vZr/AeCKJkyzjTAe30+DPwsOPynwT/iEjtJTJ3fYtv+ursMs=\r\n" + 
			"-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5cXV0TzEOVNO+SRCFrZX\r\n" + 
			"rIo947RredHQKmiykABoIdGT9Wp/fcloYwSxpV+C6UDL/UqNzdgkNpUNsNoIIpmq\r\n" + 
			"naMmoGULfRUm/cv2ElbLQNN+O1rwykxFffiQ4hqj4+vnYheNfyiGnuppJKsIMEo2\r\n" + 
			"Gv7PeIPbbs5iiNeektZbNjfEeQ7VS+eDGW6PkTxvSraPzK+p0G/dsOPecMjkQpgn\r\n" + 
			"1dzu6s4WJPhHJyz6BF3ty+W+uGFibxWD1DIjRtNZaUTh9oKf3sZIDkxxBBMcXFu5\r\n" + 
			"Alj3Jcd/LotwHMQpKcEtG2FFBZuMzyJ2dt2X7wp5ZrJJU2BhvH0UD7AzFP9LFF95\r\n" + 
			"BQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
