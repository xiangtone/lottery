package com.iwt.yt.api.base;

import java.io.Serializable;

public abstract interface IMessage<HeadT extends Serializable, BodyT extends Serializable> extends Serializable
{
  public abstract HeadT getHead();

  public abstract void setHead(HeadT paramHeadT);

  public abstract BodyT getBody();

  public abstract void setBody(BodyT paramBodyT);
}

/* Location:           D:\youka-work\git-source\lottery\lottery-interface\lottomagic-channel-comm-plugin-1.0-SNAPSHOT.jar
 * Qualified Name:     com.iwt.vasoss.bsf.agent.lottomagic.channel.comm.plugin.api.base.IMessage
 * JD-Core Version:    0.6.2
 */