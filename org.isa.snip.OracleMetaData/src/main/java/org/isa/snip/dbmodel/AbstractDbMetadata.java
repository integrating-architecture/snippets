/*Copyright by www.integrating-architecture.de

Use and redistribution in source and binary forms,
with or without modification, are permitted WITHOUT restriction of any kind.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE LIABLE 
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/
package org.isa.snip.dbmodel;

import java.io.Serializable;

/**
 * Abstract db metadata base class.
 */
public abstract class AbstractDbMetadata implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	protected AbstractDbMetadata() {
	}

	/**
	 */
	protected AbstractDbMetadata(String pName) {
		setName(pName);
	}

	private String name = "";
	private String sql = "";
	private boolean isSysObject = false;

	/**
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the isSysObject
	 */
	public boolean isSysObject() {
		return isSysObject;
	}

	/**
	 * @param isSysObject the isSysObject to set
	 */
	public void setSysObject(boolean isSysObject) {
		this.isSysObject = isSysObject;
	}
}
